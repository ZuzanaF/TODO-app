package cz.czechitas.todo.database.dao;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import cz.czechitas.todo.database.model.TaskModel;
import cz.czechitas.todo.entity.TaskEntity;


public class TaskDAO implements DAO<TaskEntity>
{
	@Override
	public long create(TaskEntity task)
	{
		TaskModel m = new TaskModel();
		m.set(task);
		return m.save();
	}


	@Override
	public TaskEntity read(long id)
	{
		TaskModel m = new Select().from(TaskModel.class).where("Id=?", id).executeSingle();
		return m.toEntity();
	}


	@Override
	public TaskEntity readFirst()
	{
		TaskModel m = new Select().from(TaskModel.class).limit(1).executeSingle();
		return m.toEntity();
	}


	@Override
	public List<TaskEntity> readAll()
	{
		List<TaskModel> modelList = new Select().from(TaskModel.class).orderBy("Date, Title ASC").execute();
		List<TaskEntity> entityList = new ArrayList<>();

		for(TaskModel m : modelList)
		{
			TaskEntity e = m.toEntity();
			entityList.add(e);
		}

		return entityList;
	}


	@Override
	public List<TaskEntity> readAll(int limit, int offset)
	{
		List<TaskModel> modelList = new Select().from(TaskModel.class).limit(limit).offset(offset).orderBy("Date, Title ASC").execute();
		List<TaskEntity> entityList = new ArrayList<>();

		for(TaskModel m : modelList)
		{
			TaskEntity e = m.toEntity();
			entityList.add(e);
		}

		return entityList;
	}


	@Override
	public long update(TaskEntity task)
	{
		TaskModel m = new Select().from(TaskModel.class).where("Id=?", task.getId()).executeSingle();
		m.set(task);
		return m.save();
	}


	@Override
	public void delete(long id)
	{
		new Delete().from(TaskModel.class).where("Id=?", id).execute();
	}


	@Override
	public void deleteAll()
	{
		new Delete().from(TaskModel.class).execute();
	}
}
