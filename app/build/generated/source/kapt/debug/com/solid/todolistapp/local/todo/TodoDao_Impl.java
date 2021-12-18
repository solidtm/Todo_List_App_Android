package com.solid.todolistapp.local.todo;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.solid.todolistapp.model.Todo;
import com.solid.todolistapp.local.todo.dao.TodoDao;

import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TodoDao_Impl implements TodoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Todo> __insertionAdapterOfTodo;

  private final EntityDeletionOrUpdateAdapter<Todo> __deletionAdapterOfTodo;

  private final EntityDeletionOrUpdateAdapter<Todo> __updateAdapterOfTodo;

  public TodoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTodo = new EntityInsertionAdapter<Todo>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `todo_table` (`id`,`taskName`,`category`,`priority`,`timeStamp`,`finished`,`categoryCardColor`,`priorityCardColor`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Todo value) {
        stmt.bindLong(1, value.getId());
        if (value.getTaskName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTaskName());
        }
        if (value.getCategory() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCategory());
        }
        if (value.getPriority() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPriority());
        }
        if (value.getTimeStamp() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getTimeStamp());
        }
        final int _tmp;
        _tmp = value.getFinished() ? 1 : 0;
        stmt.bindLong(6, _tmp);
        stmt.bindLong(7, value.getCategoryCardColor());
        stmt.bindLong(8, value.getPriorityCardColor());
      }
    };
    this.__deletionAdapterOfTodo = new EntityDeletionOrUpdateAdapter<Todo>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `todo_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Todo value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfTodo = new EntityDeletionOrUpdateAdapter<Todo>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `todo_table` SET `id` = ?,`taskName` = ?,`category` = ?,`priority` = ?,`timeStamp` = ?,`finished` = ?,`categoryCardColor` = ?,`priorityCardColor` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Todo value) {
        stmt.bindLong(1, value.getId());
        if (value.getTaskName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTaskName());
        }
        if (value.getCategory() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCategory());
        }
        if (value.getPriority() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPriority());
        }
        if (value.getTimeStamp() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getTimeStamp());
        }
        final int _tmp;
        _tmp = value.getFinished() ? 1 : 0;
        stmt.bindLong(6, _tmp);
        stmt.bindLong(7, value.getCategoryCardColor());
        stmt.bindLong(8, value.getPriorityCardColor());
        stmt.bindLong(9, value.getId());
      }
    };
  }

  @Override
  public void addTodo(final Todo todo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTodo.insert(todo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTodo(final Todo todo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTodo.handle(todo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateTodo(final Todo todo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTodo.handle(todo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Todo>> readAllData() {
    final String _sql = "SELECT * FROM todo_table ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"todo_table"}, false, new Callable<List<Todo>>() {
      @Override
      public List<Todo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTaskName = CursorUtil.getColumnIndexOrThrow(_cursor, "taskName");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfTimeStamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timeStamp");
          final int _cursorIndexOfFinished = CursorUtil.getColumnIndexOrThrow(_cursor, "finished");
          final int _cursorIndexOfCategoryCardColor = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryCardColor");
          final int _cursorIndexOfPriorityCardColor = CursorUtil.getColumnIndexOrThrow(_cursor, "priorityCardColor");
          final List<Todo> _result = new ArrayList<Todo>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Todo _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTaskName;
            _tmpTaskName = _cursor.getString(_cursorIndexOfTaskName);
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpPriority;
            _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
            final String _tmpTimeStamp;
            _tmpTimeStamp = _cursor.getString(_cursorIndexOfTimeStamp);
            final boolean _tmpFinished;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFinished);
            _tmpFinished = _tmp != 0;
            final int _tmpCategoryCardColor;
            _tmpCategoryCardColor = _cursor.getInt(_cursorIndexOfCategoryCardColor);
            final int _tmpPriorityCardColor;
            _tmpPriorityCardColor = _cursor.getInt(_cursorIndexOfPriorityCardColor);
            _item = new Todo(_tmpId,_tmpTaskName,_tmpCategory,_tmpPriority,_tmpTimeStamp,_tmpFinished,_tmpCategoryCardColor,_tmpPriorityCardColor);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
