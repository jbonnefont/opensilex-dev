<template>
  <div>
    <b-button
      @click="showCreateForm"
      variant="phis"
    >{{$t('component.project.add')}}</b-button>

    <opensilex-core-ProjectForm
      ref="ProjectForm"
      @onCreate="callCreateProjectService"
      @onUpdate="callUpdateProjectService"
    ></opensilex-core-ProjectForm>

    <opensilex-core-ProjectTable
      ref="ProjectTable"
      @onEdit="editProject"
      @onDelete="deleteProject"
    ></opensilex-core-ProjectTable>
  </div>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import { ProjectsService } from "../../lib/api/projects.service";
import { ProjectCreationDTO } from "../../lib/model/projectCreationDTO";
import HttpResponse, { OpenSilexResponse } from "../../lib//HttpResponse";
import VueRouter from "vue-router";

@Component
export default class ProjectView extends Vue {
  $opensilex: any;
  $store: any;
  $router: VueRouter;

  get user() {
    return this.$store.state.user;
  }

  currentPage: number = 1;
  pageSize = 20;
  totalRow = 0;
  sortBy = "alias";
  sortDesc = false;

  private alias: any = "";
  set aliasPattern(value: string) {
    console.log(value);
    this.alias = value;
    let tableRef: any = this.$refs.tableRef;
    tableRef.refresh();
  }

  get aliasPattern() {
    return this.alias;
  }

  created() {
    let query: any = this.$route.query;
    if (query.aliasPattern) {
      this.aliasPattern = decodeURI(query.aliasPattern);
    }
    if (query.pageSize) {
      this.pageSize = parseInt(query.pageSize);
    }
    if (query.currentPage) {
      this.currentPage = parseInt(query.currentPage);
    }
    if (query.sortBy) {
      this.sortBy = decodeURI(query.sortBy);
    }
    if (query.sortDesc) {
      this.sortDesc = query.sortDesc == "true";
    }
  }

  fields = [
    {
      key: "alias",
      sortable: true
    },
    {
      key: "uri",
      sortable: true
    },
    {
      key: "comment",
      sortable: true
    },
    {
      key: "actions"
    }
  ];

  refresh() {
    let tableRef: any = this.$refs.tableRef;
    tableRef.refresh();
  }

  loadData() {
    let service: ProjectsService = this.$opensilex.getService(
      "opensilex.ProjectsService"
    );

    let orderBy = [];
    if (this.sortBy) {
      let orderByText = this.sortBy + "=";
      if (this.sortDesc) {
        orderBy.push(orderByText + "desc");
      } else {
        orderBy.push(orderByText + "asc");
      }
    }

    return "";
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
