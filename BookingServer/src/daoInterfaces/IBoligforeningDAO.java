package daoInterfaces;

import java.sql.SQLException;
import java.util.List;
import data.BoligForening;
import exceptions.DALException;
import exceptions.DBConnectException;

public interface IBoligforeningDAO {
public List<BoligForening> getAllBoligForening() throws DBConnectException, DALException, SQLException;
	public BoligForening getBoligForening(int id) throws DBConnectException, DALException, SQLException ;
	public void opretBoligforening(BoligForening bf) throws DBConnectException, DALException, SQLException ;
	public void updateBoligforening(int id);
	public void deleteBoligForening(int boligforeningID) throws DALException, DBConnectException, SQLException;
}
