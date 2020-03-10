import { ApiServiceBinder } from './lib';
import ProjectView from './components/projects/ProjectView.vue';
import ProjectForm from './components/projects/ProjectForm.vue';
import ProjectTable from './components/projects/ProjectTable.vue';

export default {
    install(Vue, options) {
        ApiServiceBinder.with(Vue.$opensilex.getServiceContainer());
    },
    
     components: {
        "opensilex-core-ProjectView": ProjectView,
        "opensilex-core-ProjectForm": ProjectForm,
        "opensilex-core-ProjectTable": ProjectTable,
     }
};