package com.test.web.dao;

import java.util.List;

import com.test.web.bean.MemberBean;

public interface MemberDao {

	public MemberBean selectMember();
	
	public List<MemberBean> selectMemberList();
	
	/**회원가임 */
	public int insertMember(MemberBean bean);
	
	/**회원 수정 */
	public int updateMember(MemberBean bean);
	
	/**회원 핫제 */
	public int deleteMember(MemberBean bean);
	
}
