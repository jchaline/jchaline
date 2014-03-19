package fr.paris.lutece.plugins.pac.bean.pacuser;

import java.util.Comparator;

import fr.paris.lutece.plugins.pac.utils.commons.DateUtils;


public class PacuserComparator implements Comparator<Pacuser>
{

    @Override
    /**
     * Le premier de la liste est le prochain à etre de pac, le dernier sera le dernier à l'être
     * RG : 
     * Globalement, analyse de la date du dernier pac pour définir l'ordre.
     *      Si cette date  n'est pas renseigné, il faut regarder la date d'entrée sur le projet
     * @return 1 si o1 doit être apres o2, -1 pour le cas contraire, 0 en cas d'égalité
     */
    public int compare( Pacuser o1, Pacuser o2 )
    {
        int result = 0;
        //1ere step, verifier les cas particulier d'égalité
        if ( !( o1.getDernierPac( ) == o2.getDernierPac( ) ) || !( o1.getDateEntree( ) == o2.getDateEntree( ) ) )
        {
            //2eme step, verifier que les deux dates possede une date de dernier pac
            result = DateUtils.compare( o1.getDernierPac( ), o2.getDernierPac( ) );
            if ( result == 0 )
            {
                result = DateUtils.compare( o1.getDateEntree( ), o2.getDateEntree( ) );
            }
        }
        return result;
    }
}
