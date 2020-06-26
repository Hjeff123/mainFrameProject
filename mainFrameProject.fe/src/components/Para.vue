<template>
  <a-form :form="form" @submit="handleSubmit" style="width: 360px">
    <a-form-item>
      <a-input
        v-focus
        v-decorator="[
            'account',
            {
              rules: [{ required: true, message: '数据集名字不能为空' }]
            }
          ]"
        placeholder="请输入要合并的数据集1"
        size="large"
      >
        <a-popover style="cursor: pointer" slot="suffix">
          <template slot="content">
            巴巴爸爸
          </template>
          <a-icon type="question-circle" />
        </a-popover>
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-input
        v-decorator="[
            'password',
            {
              rules: [{ required: true, message: '数据集名字不能为空' }]
            }
          ]"
        size="large"
        placeholder="请输入要合并的数据集2"
      >
        <a-popover style="cursor: pointer" slot="suffix">
          <template slot="content">
            巴巴爸爸
          </template>
          <a-icon type="question-circle" />
        </a-popover>
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-input
        v-decorator="[
            'address',
            {
              rules: [{ required: true, message: '地址不能为空' }]
            }
          ]"
        size="large"
        placeholder="请输入存放合并结果的数据集"
      >
        <a-popover style="cursor: pointer" slot="suffix">
          <template slot="content">
            巴巴爸爸
          </template>
          <a-icon type="question-circle" />
        </a-popover>
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-button
        type="primary"
        html-type="submit"
        size="large"
        block
        :loading="isLoading"
      >提交</a-button
      >
    </a-form-item>
  </a-form>
</template>

<script>
    export default {
        name: "Para"
    }
</script>

<style scoped>

</style>
