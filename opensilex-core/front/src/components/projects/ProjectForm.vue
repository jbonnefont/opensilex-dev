<template>
  
</template>

<script lang="ts">
import { Component, Prop } from "vue-property-decorator";
import Vue from "vue";
import VueRouter from "vue-router";
import { ProjectCreationDTO } from "../../lib/model/projectCreationDTO";

@Component
export default class ProjectForm extends Vue {
  $opensilex: any;
  $store: any;
  $router: VueRouter;

  get user() {
    return this.$store.state.user;
  }
}
</script>

<style scoped lang="scss">
</style>

