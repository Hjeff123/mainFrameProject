<template>
  <div class="auth">
    <div class="container">
      <LoginForm />
    </div>
  </div>
</template>

<script>
  import LoginForm from "@/components/LoginForm";

  export default {
    components: { LoginForm }
  };
</script>

<style lang="scss" scoped>
  .auth {
    height: 100vh;
    width: 100vw;
    background: #2c3e50;
  }

  .container {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    background: rgba(255, 255, 255, 0.7);

    padding: 32px;
    border-radius: 4px;
  }
</style>
