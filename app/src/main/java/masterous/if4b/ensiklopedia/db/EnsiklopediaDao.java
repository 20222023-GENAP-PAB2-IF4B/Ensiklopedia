package masterous.if4b.ensiklopedia.db;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface EnsiklopediaDao {
    @Insert
    void insertEnsiklopedia(Ensiklopedia ensiklopedia);

    @Query("SELECT * FROM ensiklopedia")
    List<Ensiklopedia> getAllEnsiklopedia();

    @Update
    int updateEnsiklopedia(Ensiklopedia ensiklopedia);

    @Query("DELETE FROM ensiklopedia WHERE id = :ensiklopediaId")
    int deleteEnsiklopedia(int ensiklopediaId);
}
