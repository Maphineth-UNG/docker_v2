<script setup>

import {ref} from 'vue';

defineProps(['note']);
</script>


<template>
  <div class="main-container">
    <form class="form-container">
      <div class="input-group">
        <label for="email">Email</label>
        <input type="email" id="email" v-model="email" placeholder="Enter your email" />
      </div>
      <div class="input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="password" placeholder="Enter your password" />
      </div>
      <div class="button-group">
        <button type="button" class="btn" @click="login">Login</button>
        <button type="button" class="btn" @click="signUp">Sign up</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      password: '',
    };
  },
  methods: {
    login() {
      console.log('Login clicked');
    },
    signUp() {
      console.log('Sign up clicked');
    },
  },
};
</script>

<style scoped>
.main-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f8f9fa;
}

.form-container {
  width: 300px;
  padding: 20px;
  border-radius: 10px;
  background-color: white;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
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

.button-group {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.btn {
  width: 80%;
  margin: 5px 0;
  padding: 10px;
  font-size: 16px;
  color: white;
  background-color: #5a7dcf;
  border: none;
  border-radius: 20px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.btn:hover {
  background-color: #4a69b2;
}
</style>
