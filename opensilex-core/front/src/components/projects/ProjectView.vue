<template>
  <div>
    <b-button @click="showCreateForm" variant="phis">{{$t('component.project.add')}}</b-button>

    <opensilex-core-ProjectForm
      ref="ProjectForm"
      @onCreate="callCreateProjectService"
      @onUpdate="callUpdateProjectService"
    ></opensilex-core-ProjectForm>

    <opensilex-core-ProjectTable ref="ProjectTable" @onEdit="editProject" @onDelete="deleteProject"></opensilex-core-ProjectTable>
  
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import { ProjectsService } from "../../lib/api/projects.service";
import { ProjectCreationDTO } from "../../lib/model/projectCreationDTO";
import HttpResponse, { OpenSilexResponse } from "../../lib//HttpResponse";

@Component
export default class ProjectView extends Vue {
  $opensilex: any;
  $store: any;
  service: ProjectsService;

  get user() {
    return this.$store.state.user;
  }

  created() {
    this.service = this.$opensilex.getService("opensilex.ProjectsService");
  }
}
</script>

<style scoped lang="scss">

.uri-info {
  text-overflow: ellipsis;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  display: inline-block;
  max-width: 300px;
}

.btn-phis {
  background-color: #00a38d;
  border: 1px solid #00a38d;
  color: #ffffff !important;
}
.btn-phis:hover,
.btn-phis:focus,
.btn-phis.active {
  background-color: #00a38d;
  border: 1px solid #00a38d;
  color: #ffffff !important;
}
.btn-phis:focus {
  outline: 0;
  -webkit-box-shadow: none;
  box-shadow: none;
}
</style>
