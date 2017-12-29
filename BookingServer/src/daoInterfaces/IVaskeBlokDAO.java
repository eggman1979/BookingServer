package daoInterfaces;


import java.sql.SQLException;
import java.util.List;
import data.VaskeBlok;
import exceptions.DALException;
import exceptions.DBConnectException;

public interface IVaskeBlokDAO {
	public void opretVaskeBlokke(int antalBlokke, int startTid, int vaskeInterval, int boligselskabID) throws DBConnectException, DALException, SQLException;
	public List<VaskeBlok> getAllVaskeBlok(int boligselskabID) throws DBConnectException, DALException;
}
