package br.com.acoaapp.main.collaborator.collaborators;

import java.util.List;

import br.com.acoaapp.data.entity.Collaborator;

interface CollaboratorsContract {

    interface View {
        void setProgressIndicator(boolean isActive);

        void showCollaboratorsList(List<Collaborator> collaboratorList);

        void showCollaboratorDetailUi(Integer collaboratorCode);

        void showAddCollaboratorUi();

        void showMessage(String message);
    }

    interface UserActionListener {
        void loadCollaboratorList(boolean doRefresh);

        void openCollaboratorDetail(Collaborator collaborator);

        void openAddCollaborator();
    }

}
