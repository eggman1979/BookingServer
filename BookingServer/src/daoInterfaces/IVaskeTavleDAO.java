package daoInterfaces;


import java.sql.SQLException;
import java.util.List;
import data.VaskeTavle;
import exceptions.DALException;
import exceptions.DBConnectException;

public interface IVaskeTavleDAO {

	public List<VaskeTavle> getAllVaskeTavler() throws DBConnectException, DALException, SQLException;
	public List<VaskeTavle> getVaskeTavler(int boligforeningID) throws DBConnectException, DALException;
	public VaskeTavle getVaskeTavle(int tavleID) throws DBConnectException, DALException, SQLException;
	public void opretVaskeTavle(VaskeTavle vaskeTavle) throws DALException, DBConnectException, SQLException;
	public void updateVaskeTavle(VaskeTavle vaskeTavle) throws DBConnectException, DALException, SQLException;

}
