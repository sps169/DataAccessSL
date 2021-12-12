package jpa.mappers;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseMapper<Dao, Dto> {
	public List<Dao> fromDTO(List<Dto> items) {
		return items.stream().map(this::fromDTO).collect(Collectors.toList());
	}

	public abstract Dao fromDTO(Dto item);

	public List<Dto> toDTO(List<Dao> items) {
		return items.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public abstract Dto toDTO(Dao item);
}
