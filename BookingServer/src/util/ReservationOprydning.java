package util;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import daoImpl.BoligforeningDAO;
import daoImpl.ReservationDAO;
import daoImpl.VaskeblokDAO;
import data.BoligForening;
import data.Reservation;
import data.VaskeBlok;
import exceptions.DALException;
import exceptions.DBConnectException;



public class ReservationOprydning {

	private final static int BUFFER = 120000;

	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	ReservationDAO resDAO;
	public ReservationOprydning(){
		resDAO = new ReservationDAO();
	}


	// Init til beregning af tidspunkterne hvor rens skal ske.
	LocalDateTime midnat = LocalDate.now().atStartOfDay();
	long midnatMilli = midnat.atZone(ZoneId.of("Europe/Copenhagen")).toInstant().toEpochMilli();
	LocalDateTime forrige = midnat.plusHours(LocalDateTime.now().getHour());
	LocalDateTime naeste = forrige.plusHours(1);
	Long naesteMillis  = naeste.atZone(ZoneId.of("Europe/Copenhagen")).toInstant().toEpochMilli();


	public void rensReservationer(){
		System.out.println("Starter rensReservationer");
		ScheduledFuture<?> countdown = scheduler.schedule(new Runnable() {
			@Override
			public void run() {
				// do the thing
				System.out.println("Out of time!");
			}}, 365, TimeUnit.DAYS);

		while (!countdown.isDone()) {
			try {
				Thread.sleep(60000);
				long tidNu = System.currentTimeMillis();

				if(tidNu >= naesteMillis && tidNu <= (naesteMillis+BUFFER )){
					midnat = LocalDate.now().atStartOfDay();
					forrige = midnat.plusHours(LocalDateTime.now().getHour());
					naeste = forrige.plusHours(1);
					naesteMillis  = naeste.atZone(ZoneId.of("Europe/Copenhagen")).toInstant().toEpochMilli();

					int vaskeBlokTid = forrige.getHour();
					System.out.println("Klokkeslettet for rens er " + forrige.toString());
					List<Reservation> resList = findGamleReservationer(vaskeBlokTid);
					resDAO.reservationBatchDelete(resList);
					DebugLog.log(this.getClass().getName() + "  Der blev slettet " + resList.size() + "reservationer");

				}else{
					System.out.println("Næste slet er " + naeste.toString());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		scheduler.shutdown();
	}	

	public List<Reservation> findGamleReservationer(int vaskeBlokTid){

		//Gå igennem alle boligselskaber, og hent vaskeblokke, der skal sammenlignes med vaskebloktid, og alle reservationer der har et vaskeblokid der er mindre eller lig den blok med vaskebloktid, skal slettes


		BoligforeningDAO bDAO = new BoligforeningDAO();
		VaskeblokDAO vbDAO = new VaskeblokDAO();

		List<Reservation> resTilSlet = new ArrayList<>();
		try {
			int vaskeBlokId = 100;
			for(BoligForening bf : bDAO.getAllBoligForening()){
				for(VaskeBlok vb : vbDAO.getAllVaskeBlok(bf.getId())){

					if(vb.getStartTid() == vaskeBlokTid){
						vaskeBlokId = vb.getBlokID();

					}
				}
				System.out.println("vaskeblok fundet " +vaskeBlokId);
				System.out.println(midnatMilli);
				List<Reservation> resList = resDAO.getReservationTilDagsDato(midnatMilli, bf.getId());
				for(Reservation r : resList){
					System.out.println("hepp");
					if(r.getVaskeBlokID() <= vaskeBlokId){
						System.out.println(" hep" );
						resTilSlet.add(r);
					}
				}

			}

		} catch (DBConnectException | DALException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resTilSlet;
	}
}

