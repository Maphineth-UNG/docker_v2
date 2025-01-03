<template>
  <div>
    <MainPage
      v-if="currentPage === 'main'"
      @switchToSignUp="showSignUpPage"
      @login="handleLogin"
    />
    <SignUpPage
      v-if="currentPage === 'signup'"
      @switchToMain="showMainPage"
      @createAccount="handleCreateAccount"
    />
    <AccountPage
      v-if="currentPage === 'account'"
      :user="loggedInUser"
      @logout="handleLogout"
    />
  </div>
</template>

<script>
import MainPage from './MainPage.vue';
import SignUpPage from './SignUpPage.vue';
import AccountPage from './AccountPage.vue';

export default {
  data() {
    return {
      currentPage: 'main', // Tracks the current page ('main', 'signup', or 'account')
      account: null, // Stores the created account details
      loggedInUser: null, // Stores the currently logged-in user
    };
  },
  components: {
    MainPage,
    SignUpPage,
    AccountPage,
  },
  methods: {
    showSignUpPage() {
      this.currentPage = 'signup';
    },
    showMainPage() {
      this.currentPage = 'main';
    },
    handleCreateAccount(accountDetails) {
      this.account = accountDetails;
      alert('Account created successfully! You can now log in.');
      this.showMainPage();
    },
    handleLogin(email, password) {
      if (this.account && this.account.email === email && this.account.password === password) {
        this.loggedInUser = this.account;
        this.currentPage = 'account';
      } else {
        alert('Invalid email or password.');
      }
    },
    handleLogout() {
      this.loggedInUser = null;
      this.showMainPage();
    },
  },
};
</script>
