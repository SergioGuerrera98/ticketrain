package com.corso.ticketrain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.ticketrain.dao.TrenoDao;
import com.corso.ticketrain.dao.interfacce.TrenoDaoInterface;
import com.corso.ticketrain.service.exceptions.TrenoNotFoundException;
import com.corso.ticketrain.treno.builder.TrenoBuilder;
import com.corso.ticketrain.treno.exceptions.TrenoException;
import com.corso.ticketrain.treno.factory.VagoneFactory;
import com.corso.ticketrain.treno.model.*;

@Transactional
@Service
public class TrenoService {
	private static Logger logger = LogManager.getLogger(TicketService.class);

	@Autowired
	private TrenoDaoInterface trenoDao;

	/** nuovo treno da sigla 
	 * @throws TrenoException */
	public void addTrain(String sigla, VagoneFactory factory) throws TrenoException {
		logger.info("TrenoService.addTrain : entering method with params[sigla = {}, factory = {}].",
				sigla, factory);
		Treno treno = new TrenoBuilder(factory).costruisciTreno(sigla);
		trenoDao.create(treno);

		logger.info("TicketUserService.acquistaTicket: exiting method");

	}

	/** nuovo treno da sigla 
	 * @throws TrenoException */
	public Treno addTrain2(String sigla, VagoneFactory factory) throws TrenoException {
		logger.info("TrenoService.addTrain2 : entering method with params[sigla = {}, factory = {}].",
			sigla, factory);
		Treno treno = null;
		try {
			treno = new TrenoBuilder(factory).costruisciTreno(sigla);
			trenoDao.create(treno);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("TrenoService.addTrain2 : exiting method with exception [e = {}]", e.getMessage());

			throw e;
		}

		logger.info("TrenoService.addTrain2: exiting method with results [treno = {}]", treno);
		return treno;
	}

	/** rimuove treno da entity */
	public boolean removeTrain(Treno treno) {
		logger.info("TrenoService.removeTrain : entering method with params[treno = {}]", treno);
		try {
			Treno found = trenoDao.retrieveById(treno.getId());

			if (treno == found) {
				trenoDao.delete(treno);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("TrenoService.removeTrain : exiting method with exception [e = {}, return = {}]", e.getMessage(), false);

			return false;
		}

		logger.info("TrenoService.removeTrain: exiting method with results [result = {}]", true);
		return true;
	}

	/** rimuove treno da id */
	public boolean removeTrain(int treno_id) {
		logger.info("TrenoService.removeTrain : entering method with params[treno_id = {}]", treno_id);
		try {
			Treno found = trenoDao.retrieveById(treno_id);

			if (found != null) {
				trenoDao.delete(found);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("TrenoService.removeTrain : exiting method with exception [e = {}, return = {}]", e.getMessage(), false);

			return false;
		}

		logger.info("TrenoService.addTrain2: exiting method with results [result = {}]", true);
		return true;
	}

	/** cerca treno per entità */
	public boolean addVagoneToIndex(Treno treno, Vagone vagone, int index) {
		logger.info("TrenoService.addVagoneToIndex : entering method with params[treno = {}, vagone = {}, index ={}]",
				treno, vagone, index);

		try {
			Treno found = trenoDao.retrieveById(treno.getId());
			if (treno != found)
				throw new TrenoNotFoundException("Treno non esiste.", treno.getCodice());

			treno.addVagone(vagone, index);
			trenoDao.update(treno);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("TrenoService.addVagoneToIndex : exiting method with exception [e = {}, return = {}]", e.getMessage(), false);

			return false;
		}

		logger.info("TrenoService.addVagoneToIndex: exiting method with results [result = {}]", true);

		return true;
	}

	/** cerca treno con id */
	public boolean addVagoneToIndex(int treno_id, Vagone vagone, int index) {
		logger.info("TrenoService.addVagoneToIndex : entering method with params[treno_id = {}, vagone = {}, index ={}]",
				treno_id, vagone, index);
		try {
			Treno found = trenoDao.retrieveById(treno_id);
			if (found == null)
				throw new TrenoNotFoundException("Treno non esiste.", ""+treno_id);

			found.addVagone(vagone, index);
			trenoDao.update(found);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("TrenoService.addVagoneToIndex : exiting method with exception [e = {}, return = {}]", e.getMessage(), false);

			return false;
		}

		logger.info("TrenoService.addVagoneToIndex: exiting method with results [result = {}]", true);

		return true;
	}

	/** crea vagone internamente al metodo  */
	public boolean addVagoneToIndex(int treno_id, int index, char vagone_sigla) {
		logger.info("TrenoService.addVagoneToIndex : entering method with params[treno_id = {}, vagone_sigla = {}, index ={}]",
				treno_id, vagone_sigla, index);
		try {
			Treno found = trenoDao.retrieveById(treno_id);
			if (found == null)
				throw new TrenoNotFoundException("Treno non esiste.", ""+treno_id);

			Vagone vagone = new VagoneFactory().costruisciVagone(vagone_sigla);
			found.addVagone(vagone, index);
			trenoDao.update(found);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("TrenoService.addVagoneToIndex : exiting method with exception [e = {}, return = {}]", e.getMessage(), false);

			return false;
		}

		logger.info("TrenoService.addVagoneToIndex: exiting method with results [result = {}]", true);

		return true;
	}

	/** rimuove vagone a indice  */
	public boolean removeVagoneAtIndex(int treno_id, int index) {
		logger.info("TrenoService.removeVagoneAtIndex : entering method with params[treno_id = {}, index ={}]",
				treno_id, index);
		try {
			Treno found = trenoDao.retrieveById(treno_id);
			if (found == null)
				throw new TrenoNotFoundException("Treno non esiste.", ""+treno_id);

			found.removeVagone(index);
			trenoDao.update(found);			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("TrenoService.removeVagoneAtIndex : exiting method with exception [e = {}, return = {}]", e.getMessage(), false);

			return false;
		}

		logger.info("TrenoService.removeVagoneAtIndex: exiting method with results [result = {}]", true);

		return true;
	}

	/** ottiene details treno da id 
	 * @throws Exception */
	public Treno getTrenoById(int treno_id) throws Exception {
		logger.info("TrenoService.getTrenoById : entering method with params[treno_id ={}]",
				treno_id);
		Treno found = null;
		try {
			found = trenoDao.retrieveById(treno_id);
			if (found == null)
				throw new TrenoNotFoundException("Treno non esiste.", ""+treno_id);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("TrenoService.getTrenoById : exiting method with exception [e = {}]",
					e.getMessage());

			throw e;
		}

		logger.info("TrenoService.getTrenoById: exiting method with results [found = {}]", found);

		return found;
	}

	public List<Treno> retrieveAll(){
		logger.info("TrenoService.retrieveAll : entering method ");
		try {
			List<Treno> trenoList = trenoDao.retrieve();
			logger.info("TrenoService.retrieveAll: exiting method with results [trenoList = {}]", trenoList);
			return trenoList;
		} catch (Exception e) {
			logger.info("TrenoService.retrieveAll : exiting method with exception [e = {}]",
					e.getMessage());
			throw e;
		}
	}

}
