package jpa.mappers;

import jpa.daos.Programmer;
import jpa.dtos.ProgrammerDTO;
import jpa.utils.TechnologiesParser;
/**
 * Class that models the Mappers with the methods from and to DTO for Mapper Programmer class
 * @author sps169, FedericoTB
 */
public class ProgrammerMapper extends BaseMapper<Programmer, ProgrammerDTO>{
	/**
	 * Method that map a ProgrammerDTO object into a Programmer object.
	 * @param item ProgrammerDTO to map
	 * @return Programmer mapped
	 */
	@Override
	public Programmer fromDTO(ProgrammerDTO item) {
		return new Programmer(item.getId(), item.getName(), item.getEntry_date(), item.getPassword(),
				TechnologiesParser.technologiesToString(item.getTechnologies()), item.getSalary(), item.getDepartment());
	}
	/**
	 * Method that map a Programmer object into a ProgrammerDTO object.
	 * @param item Programmer to map
	 * @return ProgrammerDTO mapped
	 */
	@Override
	public ProgrammerDTO toDTO(Programmer item) {
		return new ProgrammerDTO(item.getId(), item.getName(), item.getEntry_date(), item.getPassword(),
				TechnologiesParser.technologiesToSet(item.getTechnologies()), item.getSalary(), item.getDepartment());
	}
}
