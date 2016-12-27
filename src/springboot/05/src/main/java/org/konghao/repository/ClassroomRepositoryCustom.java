package org.konghao.repository;

import java.util.List;

import org.konghao.model.ClassroomDto;
import org.springframework.transaction.annotation.Transactional;

public interface ClassroomRepositoryCustom {
	
	@Transactional
	public void delete(int cla);
	
	/**
	 * 查询班级dto
	 * @return
	 */
	public List<ClassroomDto> listClassroomDto();
}
