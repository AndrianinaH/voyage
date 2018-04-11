package S6.voyage.baseModel;

import java.io.Serializable;

public class BaseModel implements Serializable
{
	protected int id;
	public BaseModel(){}
	public BaseModel(int id){
		this.id=id;
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
}
