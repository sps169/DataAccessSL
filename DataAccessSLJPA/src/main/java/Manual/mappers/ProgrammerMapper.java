//package Manual.mappers;
//
//import Manual.daos.Programmer;
//import Manual.dtos.ProgrammerDTO;
//import Manual.utils.TechnologiesParser;
//
//public class ProgrammerMapper extends BaseMapper<Programmer, ProgrammerDTO>{
//
//	@Override
//	public Programmer fromDTO(ProgrammerDTO item) {
//		return new Programmer(item.getId(), item.getName(), item.getEntry_date(), item.getPassword(),
//				TechnologiesParser.technologiesToString(item.getTechnologies()), item.getSalary(), item.getDepartment().getId());
//	}
//
//	@Override
//	public ProgrammerDTO toDTO(Programmer item) {
//		return new ProgrammerDTO(item.getId(), item.getName(), item.getEntry_date(), item.getPassword(),
//				TechnologiesParser.technologiesToSet(item.getTechnologies()), item.getSalary());
//	}
//}
