package sio.tp3;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.util.Callback;
import sio.tp3.Model.Tache;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class TP3Controller implements Initializable {
    private HashMap<String, HashMap<String, ArrayList<Tache>>> mesTaches;
    TreeItem racine;
    @FXML
    private ListView lstThemes;
    @FXML
    private ListView lstProjets;
    @FXML
    private TreeView tvTaches;
    @FXML
    private ComboBox cboDeveloppeurs;
    @FXML
    private Button cmdValider;
    @FXML
    private TextField txtNomTache;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mesTaches = new HashMap<>();
        racine = new TreeItem("Mes tâches");
        racine.setExpanded(true);
        tvTaches.setRoot(racine);

        cboDeveloppeurs.getItems().addAll("Enzo","Noa","Lilou","Milo");
        cboDeveloppeurs.getSelectionModel().selectFirst();

        lstThemes.getItems().addAll("Mobile","Web","Réseau");

        for(int i = 1 ; i<=10 ; i++)
        {
            lstProjets.getItems().add("Projet n°" + i);
        }
    }

    @FXML
    public void cmdValiderClicked(Event event)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur sur vos informations");
        alert.setHeaderText("");

        String nomTache = txtNomTache.getText();

        if (lstThemes.getSelectionModel().getSelectedItem() == null) {
            alert.setContentText("Veuillez sélectionner le thème");
            alert.showAndWait();
            return;
        }

        if (lstProjets.getSelectionModel().getSelectedItem() == null) {
            alert.setContentText("Veuillez sélectionner un projet");
            alert.showAndWait();
            return;
        }

        if (nomTache.isEmpty()) {
            alert.setContentText("Veuillez entrer le nom de la tâche");
            alert.showAndWait();
            return;
        }

        String selectedTheme = lstThemes.getSelectionModel().getSelectedItem().toString();
        String selectedProjet = lstProjets.getSelectionModel().getSelectedItem().toString();
        Tache task = new Tache(nomTache, cboDeveloppeurs.getSelectionModel().getSelectedItem().toString(), false);


        if (!mesTaches.containsKey(selectedTheme)) {
            mesTaches.put(selectedTheme, new HashMap<>());
        }

        if (!mesTaches.get(selectedTheme).containsKey(selectedProjet)) {
            mesTaches.get(selectedTheme).put(selectedProjet, new ArrayList<>());
        }

        mesTaches.get(selectedTheme).get(selectedProjet).add(task);

        clearForm();
        updateTreeView();
    }


    private void clearForm() {
        txtNomTache.clear();
        cboDeveloppeurs.getSelectionModel().selectFirst();
    }

    private void updateTreeView() {
        racine.getChildren().clear();

        mesTaches.forEach((theme, projets) -> {
            TreeItem<String> themeNode = new TreeItem<>(theme);

            projets.forEach((projet, tasks) -> {
                TreeItem<String> projetNode = new TreeItem<>(projet);

                tasks.forEach(task -> {
                    TreeItem<String> taskNode = new TreeItem<>(
                            task.getNomDeveloppeur() + " : " + task.getNomTache() + " : " +
                                    (task.isEstTerminee() ? "true" : "false")
                    );
                    projetNode.getChildren().add(taskNode);
                    taskNode.setExpanded(true);
                });

                themeNode.getChildren().add(projetNode);
                projetNode.setExpanded(true);
            });

            racine.getChildren().add(themeNode);
            themeNode.setExpanded(true);
        });
    }


    @FXML
    public void tvTachesClicked(Event event) {
        TreeItem<?> selected = (TreeItem<?>) tvTaches.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        if (selected.getValue().toString().contains(":")) {
            String projet = selected.getParent().getValue().toString();
            String theme = selected.getParent().getParent().getValue().toString();
            String taskText = selected.getValue().toString();
            String[] parts = taskText.split(" : ");
            String nomDev = parts[0];
            String nomTache = parts[1];

            ArrayList<Tache> tasks = mesTaches.get(theme).get(projet);

            // 1er Méthode
            tasks.forEach(task -> {
                if (task.getNomDeveloppeur().equals(nomDev) && task.getNomTache().equals(nomTache)) {
                    task.setEstTerminee(!task.isEstTerminee());
                }
            });

            /** 2eme Méthode
            Tache task =  mesTaches.get(theme).get(projet).stream().filter(t ->
                    t.getNomDeveloppeur().equals(nomDev) && t.getNomTache().equals(nomTache)).findAny().orElse(null);

            if (task.isEstTerminee()) {
                task.setEstTerminee(!task.isEstTerminee());
            }
            **/

            updateTreeView();
        }
    }
}