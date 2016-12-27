package org.konghao.reposiotry.kit;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * 仅仅只是对size进行了封装，太过简单
 * @author konghao
 *
 */
public class SimplePageBuilder {
	
	/**
	 * 以常量的形式存储，在实际的运用中应该从properties文件中取得，思路都一样
	 * */
	public static final int size = 15;
	
	public static Pageable generate(int page,int size,Sort sort) {
		if(sort==null) return new PageRequest(page, size);
		return new PageRequest(page, size, sort);
	}
	
	public static Pageable generate(int page) {
		return generate(page,size,null);
	}
	
	public static Pageable generate(int page,Sort sort) {
		return generate(page,size,sort);
	}
}
