<template>
  <div class="account-container">
    <!-- <h1>Room Reservation</h1> -->
    <h2>Welcome, {{ user.firstName }} {{ user.lastName }}</h2>
    <!-- <p>Email: {{ user.email }}</p> -->

    <div class="reservations-section">
      <h2>My Reservations</h2>
      <button class="btn create-btn" @click="openCreateModal">Create Reservation</button>
      <table class="reservation-table">
        <thead>
          <tr>
            <th>Reservation ID</th>
            <th>Room Name</th>
            <th>Date</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(reservation, index) in reservations" :key="reservation.id">
            <td>{{ reservation.id }}</td>
            <td>{{ reservation.room }}</td>
            <td>{{ reservation.date }}</td>
            <td>{{ reservation.startTime }}</td>
            <td>{{ reservation.endTime }}</td>
            <td>
              <button class="btn modify-btn" @click="editReservation(index)">Modify</button>
              <button class="btn delete-btn" @click="deleteReservation(index)">Delete</button>
            </td>
          </tr>
          <tr v-if="reservations.length === 0">
            <td colspan="6" class="no-data">No reservations available</td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Edit Reservation Modal -->
    <div v-if="showEditModal" class="modal">
      <div class="modal-content">
        <h3>Edit Reservation</h3>
        <form @submit.prevent="saveChanges">
          <div class="form-group">
            <label for="editRoom">Room Name</label>
            <input type="text" id="editRoom" v-model="editForm.room" required />
          </div>
          <div class="form-group">
            <label for="editDate">Date</label>
            <input type="date" id="editDate" v-model="editForm.date" required />
          </div>
          <div class="form-group">
            <label for="editStartTime">Start Time</label>
            <input type="time" id="editStartTime" v-model="editForm.startTime" required />
          </div>
          <div class="form-group">
            <label for="editEndTime">End Time</label>
            <input type="time" id="editEndTime" v-model="editForm.endTime" required />
          </div>
          <div class="modal-actions">
            <button type="submit" class="btn save-btn">Save</button>
            <button type="button" class="btn cancel-btn" @click="closeModal">Cancel</button>
          </div>
        </form>
      </div>
    </div>

    <!-- Create Reservation Modal -->
    <div v-if="showCreateModal" class="modal">
      <div class="modal-content">
        <h3>Create Reservation</h3>
        <form @submit.prevent="createReservation">
          <div class="form-group">
            <label for="createRoom">Room Name</label>
            <input type="text" id="createRoom" v-model="createForm.room" required />
          </div>
          <div class="form-group">
            <label for="createDate">Date</label>
            <input type="date" id="createDate" v-model="createForm.date" required />
          </div>
          <div class="form-group">
            <label for="createStartTime">Start Time</label>
            <input type="time" id="createStartTime" v-model="createForm.startTime" required />
          </div>
          <div class="form-group">
            <label for="createEndTime">End Time</label>
            <input type="time" id="createEndTime" v-model="createForm.endTime" required />
          </div>
          <div class="modal-actions">
            <button type="submit" class="btn save-btn">Save</button>
            <button type="button" class="btn cancel-btn" @click="closeCreateModal">Cancel</button>
          </div>
        </form>
      </div>
    </div>

    <button class="btn logout-btn" @click="$emit('logout')">Logout</button>
  </div>
</template>

<script>
export default {
  props: {
    user: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      reservations: [
        { id: 1, room: 'Room A', date: '2024-01-01', startTime: '09:00', endTime: '11:00' },
        { id: 2, room: 'Room B', date: '2024-01-02', startTime: '14:00', endTime: '16:00' },
      ],
      showEditModal: false,
      showCreateModal: false,
      editForm: {
        id: null,
        room: '',
        date: '',
        startTime: '',
        endTime: '',
      },
      createForm: {
        room: '',
        date: '',
        startTime: '',
        endTime: '',
      },
      currentReservationIndex: null,
    };
  },
  methods: {
    editReservation(index) {
      this.editForm = JSON.parse(JSON.stringify(this.reservations[index]));
      this.currentReservationIndex = index;
      this.showEditModal = true;
    },
    saveChanges() {
      if (this.currentReservationIndex !== null) {
        if (
          !this.editForm.room ||
          !this.editForm.date ||
          !this.editForm.startTime ||
          !this.editForm.endTime
        ) {
          alert('All fields are required.');
          return;
        }
        this.reservations.splice(this.currentReservationIndex, 1, { ...this.editForm });
        this.closeModal();
        alert('Reservation updated successfully!');
      } else {
        alert('No reservation selected for modification.');
      }
    },
    deleteReservation(index) {
      if (confirm('Are you sure you want to delete this reservation?')) {
        this.reservations.splice(index, 1);
      }
    },
    closeModal() {
      this.showEditModal = false;
      this.editForm = {
        id: null,
        room: '',
        date: '',
        startTime: '',
        endTime: '',
      };
      this.currentReservationIndex = null;
    },
    openCreateModal() {
      this.showCreateModal = true;
    },
    closeCreateModal() {
      this.showCreateModal = false;
      this.createForm = {
        room: '',
        date: '',
        startTime: '',
        endTime: '',
      };
    },
    createReservation() {
      if (
        !this.createForm.room ||
        !this.createForm.date ||
        !this.createForm.startTime ||
        !this.createForm.endTime
      ) {
        alert('All fields are required.');
        return;
      }
      const newReservation = {
        id: this.reservations.length + 1, // Incremental ID
        ...this.createForm,
      };
      this.reservations.push(newReservation);
      this.closeCreateModal();
      alert('Reservation created successfully!');
    },
  },
};
</script>



<style scoped>
/* Styling the page and modal */
.account-container {
  text-align: center;
  margin: 20px;
}

.reservations-section {
  margin-top: 20px;
}

.reservation-table {
  width: 100%;
  border-collapse: collapse;
  margin: 20px auto;
}

.reservation-table th,
.reservation-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
}

.reservation-table th {
  background-color: #f4f4f4;
  font-weight: bold;
}

.no-data {
  text-align: center;
  color: #888;
}

.btn {
  margin: 0 5px;
  padding: 5px 10px;
  font-size: 14px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.modify-btn {
  background-color: #5a7dcf;
  color: white;
}

.modify-btn:hover {
  background-color: #4a69b2;
}

.delete-btn {
  background-color: #d9534f;
  color: white;
}

.delete-btn:hover {
  background-color: #c9302c;
}

.logout-btn {
  margin-top: 20px;
  background-color: #5a7dcf;
  color: white;
}

.logout-btn:hover {
  background-color: #4a69b2;
}

/* Styling for the green layout container */
/* Green button styled like the Logout button */
.create-btn {
  background-color: #28a745; /* Green background */
  color: white; /* White text */
  padding: 10px 20px; /* Similar padding as Logout */
  font-size: 16px; /* Same font size */
  border: none; /* No border */
  border-radius: 5px; /* Same rounded corners */
  cursor: pointer; /* Pointer cursor */
  transition: background-color 0.3s ease; /* Smooth hover effect */
  margin: 10px 0; /* Consistent margin */
  display: inline-block; /* Inline-block to match button alignment */
}
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background: white;
  padding: 20px;
  border-radius: 10px;
  width: 300px;
  text-align: left;
}

.modal-actions {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
}

.save-btn {
  background-color: #5cb85c;
  color: white;
}

.save-btn:hover {
  background-color: #4cae4c;
}

.cancel-btn {
  background-color: #d9534f;
  color: white;
}

.cancel-btn:hover {
  background-color: #c9302c;
}
</style>
