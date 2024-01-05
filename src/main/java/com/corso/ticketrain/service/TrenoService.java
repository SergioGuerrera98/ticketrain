package com.corso.ticketrain.service;

import java.util.List;

import javax.transaction.Transactional;

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
	
	@Autowired
	private TrenoDaoInterface trenoDao;

	/** nuovo treno da sigla */
	public boolean addTrain(String sigla, VagoneFactory factory) {
		try {
			Treno treno = new TrenoBuilder(factory).costruisciTreno(sigla);
			trenoDao.create(treno);

		} catch (TrenoException te) {
			te.printStackTrace();
			return false;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	/** nuovo treno da sigla 
	 * @throws TrenoException */
	public Treno addTrain2(String sigla, VagoneFactory factory) throws TrenoException {
		Treno treno = null;
		try {
			treno = new TrenoBuilder(factory).costruisciTreno(sigla);
			trenoDao.create(treno);

		} catch (TrenoException te) {
			te.printStackTrace();
			throw te;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return treno;
	}

	/** rimuove treno da entity */
	public boolean removeTrain(Treno treno) {
		try {
			Treno found = trenoDao.retrieveById(treno.getId());
			
			if (treno == found) {
				trenoDao.delete(treno);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/** rimuove treno da id */
	public boolean removeTrain(int treno_id) {
		try {
			Treno found = trenoDao.retrieveById(treno_id);
			
			if (found != null) {
				trenoDao.delete(found);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/** cerca treno per entità */
	public boolean addVagoneToIndex(Treno treno, Vagone vagone, int index) {
		try {
			Treno found = trenoDao.retrieveById(treno.getId());
			if (treno != found)
				throw new TrenoNotFoundException("Treno non esiste.", treno.getCodice());

			treno.addVagone(vagone, index);
			trenoDao.update(treno);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/** cerca treno con id */
	public boolean addVagoneToIndex(int treno_id, Vagone vagone, int index) {
		try {
			Treno found = trenoDao.retrieveById(treno_id);
			if (found == null)
				throw new TrenoNotFoundException("Treno non esiste.", ""+treno_id);

			found.addVagone(vagone, index);
			trenoDao.update(found);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/** crea vagone internamente al metodo  */
	public boolean addVagoneToIndex(int treno_id, int index, char vagone_sigla) {
		try {
			Treno found = trenoDao.retrieveById(treno_id);
			if (found == null)
				throw new TrenoNotFoundException("Treno non esiste.", ""+treno_id);
			
			Vagone vagone = new VagoneFactory().costruisciVagone(vagone_sigla);
			found.addVagone(vagone, index);
			trenoDao.update(found);			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/** rimuove vagone a indice  */
	public boolean removeVagoneAtIndex(int treno_id, int index) {
		try {
			Treno found = trenoDao.retrieveById(treno_id);
			if (found == null)
				throw new TrenoNotFoundException("Treno non esiste.", ""+treno_id);
			
			found.removeVagone(index);
			trenoDao.update(found);			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/** ottiene details treno da id 
	 * @throws Exception */
	public Treno getTrenoById(int treno_id) throws Exception {
		Treno found = null;
		try {
			found = trenoDao.retrieveById(treno_id);
			if (found == null)
				throw new TrenoNotFoundException("Treno non esiste.", ""+treno_id);
				
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return found;
	}
	
	public List<Treno> retrieveAll(){
		try {
			return trenoDao.retrieve();
		} catch (Exception e) {
			throw e;
		}
	}

}
