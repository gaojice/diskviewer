package com.gaojice.diskviewer.dao;

import org.apache.ibatis.annotations.Insert;

import com.gaojice.diskviewer.entity.DiskFile;

public interface DiskFileDao {
	@Insert("insert into disk_file(id,createDate,lastModified,name,type,size1,parent) values(#{id},#{createDate},#{lastModified},#{name},#{type},#{size},#{parent.id})")
	public void insert(DiskFile diskFile);

	@Insert("update disk_file set size1=#{param1} where id=#{param2}")
	public void update(Long size, String id);

}
