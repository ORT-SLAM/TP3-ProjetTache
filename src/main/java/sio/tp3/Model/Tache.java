package sio.tp3.Model;

public class Tache
{
    private String nomTache;
    private String nomDeveloppeur;
    private boolean estTerminee;

    public Tache(String nomTache, String nomDeveloppeur, boolean estTerminee) {

        this.nomTache = nomTache;
        this.nomDeveloppeur = nomDeveloppeur;
        this.estTerminee = estTerminee;
    }

    public void setEstTerminee(boolean estTerminee) {
        this.estTerminee = estTerminee;
    }

    public String getNomTache() {
        return nomTache;
    }

    public String getNomDeveloppeur() {
        return nomDeveloppeur;
    }

    public boolean isEstTerminee() {
        return estTerminee;
    }
}
