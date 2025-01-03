<template>
  <div class="signup-container">
    <form class="form-container" @submit.prevent="createAccount">
      <div class="input-group">
        <label for="firstName">First name</label>
        <input type="text" id="firstName" v-model="firstName" placeholder="Enter your first name" />
      </div>
      <div class="input-group">
        <label for="lastName">Last name</label>
        <input type="text" id="lastName" v-model="lastName" placeholder="Enter your last name" />
      </div>
      <div class="input-group">
        <label for="email">Email</label>
        <input type="email" id="email" v-model="email" placeholder="Enter your email" />
      </div>
      <div class="input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="password" placeholder="Password" />
      </div>
      <div class="input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" placeholder="Confirm Password" />
      </div>
      <div class="button-group">
        <button type="submit" class="btn">Create account</button>
        <button type="button" class="btn back-btn" @click="$emit('switchToMain')">Back</button>
      </div>
      <div class="error-message" v-if="errorMessage">{{ errorMessage }}</div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      confirmPassword: '',
      errorMessage: '',
    };
  },
  methods: {
    createAccount() {
      this.errorMessage = '';

      if (!this.firstName || !this.lastName || !this.email || !this.password || !this.confirmPassword) {
        this.errorMessage = 'All fields are required.';
        return;
      }

      const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailPattern.test(this.email)) {
        this.errorMessage = 'Please enter a valid email address.';
        return;
      }

      if (this.password !== this.confirmPassword) {
        this.errorMessage = 'Passwords do not match.';
        return;
      }

      this.$emit('createAccount', {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        password: this.password,
      });
    },
  },
};
</script>


<style scoped>
.signup-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f8f9fa; /* Light gray background */
}

.form-container {
  width: 300px;
  padding: 20px;
  border-radius: 10px;
  background-color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Subtle shadow for a clean look */
}

.input-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-size: 14px;
  color: #333;
}

input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 14px;
}

input::placeholder {
  color: #aaa; /* Placeholder color */
}

.button-group {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.btn {
  width: 100%;
  padding: 10px;
  font-size: 16px;
  color: white;
  background-color: #5a7dcf; /* Blue button background */
  border: none;
  border-radius: 20px; /* Rounded corners */
  cursor: pointer;
  margin-bottom: 10px;
  transition: background-color 0.3s ease; /* Smooth hover effect */
}

.btn:hover {
  background-color: #4a69b2; /* Darker blue on hover */
}

.back-btn {
  background-color: #d9534f; /* Red for back button */
}

.back-btn:hover {
  background-color: #c9302c; /* Darker red on hover */
}

.error-message {
  margin-top: 10px;
  color: red;
  text-align: center;
  font-size: 14px;
}
</style>
