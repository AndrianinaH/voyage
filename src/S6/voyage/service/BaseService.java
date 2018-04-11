package S6.voyage.service;

import java.util.List;

import S6.voyage.baseModel.BaseModel;
import S6.voyage.dao.HibernateDao;

public class BaseService {
	protected HibernateDao hibernate;
	public List<BaseModel> findAll(BaseModel obj,int...limit) throws Exception
	{
		return hibernate.findAll(obj,limit);
	}
	public void findById(BaseModel obj) throws Exception
	{
		hibernate.findById(obj);
    }
	public void save(BaseModel obj)throws Exception
	{
		hibernate.save(obj);
	}
	public void update(BaseModel obj)throws Exception
	{
		hibernate.update(obj);
	}
	public void delete(BaseModel obj)throws Exception
	{
		hibernate.delete(obj);
	}
	public int count(BaseModel obj)throws Exception
	{
		return hibernate.getCount(obj);
	}
	
	
	
	public HibernateDao getHibernate() {
        return hibernate;
    }
    public void setHibernate(HibernateDao hibernate) {
        this.hibernate = hibernate;
    }
}
