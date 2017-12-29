package daoInterfaces;


import java.sql.SQLException;
import java.util.List;
import data.Bruger;
import exceptions.DALException;
import exceptions.DBConnectException;

public interface IBrugerDAO {
	public List<Bruger> getAllBrugere() throws DBConnectException, DALException, SQLException ;
	public Bruger getBruger(int id) throws DBConnectException, DALException, SQLException;
	public void opretBruger(Bruger bruger) throws DBConnectException, DALException, SQLException;
	public Bruger login(int id, String pass) throws DBConnectException, DALException, SQLException;
	public void deleteBruger(int brugerID) throws DALException, SQLException ;

}
