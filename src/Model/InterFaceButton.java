package Model;

import java.util.Vector;

public interface InterFaceButton<T> {
	
	void Add(T t);
	void Update(T t);
	void Dalete(T t);
	void ShowInfomation();
	Vector<T> Search(String rollno);
	Vector<String> getID();
	Vector<String> getName();

}
