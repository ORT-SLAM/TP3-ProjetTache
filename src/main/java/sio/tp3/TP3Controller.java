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

    }

    @FXML
    public void tvTachesClicked(Event event)  {

    }
}