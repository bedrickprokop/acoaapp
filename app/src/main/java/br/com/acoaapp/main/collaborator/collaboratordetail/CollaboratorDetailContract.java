package br.com.acoaapp.main.collaborator.collaboratordetail;

import br.com.acoaapp.data.entity.Collaborator;

interface CollaboratorDetailContract {

    interface View {

        void setProgressIndicator(boolean isActive);

        void showCollaborator(Collaborator collaborator);

        void showCollaboratorsUi();

        void showAddCollaboratorUi(Collaborator collaborator);

        void showMessage(String message);
    }

    interface UserActionListener {

        void loadCollaborator(Integer collaboratorCode);

        void openCollaboratorList();

        void openAddCollaborator();
    }
}
