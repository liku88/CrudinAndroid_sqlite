package in.mangaldeep;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DatabaseHelper databaseHelper;
EditText editTextId , editTextName, editTextEmail, editTextCC;
Button buttonAdd, buttonDelete , buttonUpdate , buttonGetData, buttonViewAll ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        editTextId = findViewById(R.id.editText_id);
        editTextName = findViewById(R.id.editText_name);
        editTextEmail = findViewById(R.id.editText_email);
        editTextCC = findViewById(R.id.editText_CC);

        buttonAdd  = findViewById(R.id.button_add);
        buttonDelete = findViewById(R.id.button_delete);
        buttonUpdate  = findViewById(R.id.button_update);
        buttonGetData = findViewById(R.id.button_view);
        buttonViewAll = findViewById(R.id.button_viewAll);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = databaseHelper.insertData(editTextName.getText().toString(),
                        editTextEmail.getText().toString(), editTextCC.getText().toString());
                if(isInserted == true){
                    Toast.makeText(MainActivity.this, "Data inserted Succesfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString();
                if (id.equals(String.valueOf(""))){
                    editTextId.setError("Please input the Id you want to know the data");

                }
                Cursor cursor = databaseHelper.getData(id);
                String data = null;
                if (cursor.moveToNext()){
                    data = "ID : "+cursor.getString(0) +"\n"+
                           "Name : "+cursor.getString(1) + "\n"+
                            "Email : "+cursor.getString(2)+ "\n"+
                            "Course-Count : "+cursor.getString(3) ;
                    showMessage("DATA", data);
                }

            }
        });

       buttonViewAll.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Cursor cursor = databaseHelper.getAllData();
               if (cursor.getCount() == 0){
                   showMessage("Error", "Nothing found in DB");
               }
               StringBuffer buffer = new StringBuffer();
               while (cursor.moveToNext()){
                   buffer.append("ID: "+cursor.getString(0)+"\n");
                   buffer.append("Name: "+cursor.getString(1)+"\n");
                   buffer.append("Email: "+cursor.getString(2)+"\n");
                   buffer.append("Course-Count: "+cursor.getString(3)+"\n\n");
                   showMessage("All data", buffer.toString());
               }
           }
       });
       buttonDelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Integer deletedRow = databaseHelper.deleteData(editTextId.getText().toString());
               if (deletedRow > 0){
                   Toast.makeText(MainActivity.this, "Deleted Succesfully", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(MainActivity.this, "Deleted Succesfully", Toast.LENGTH_SHORT).show();
               }
           }
       });
       buttonUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               boolean isUpdate = databaseHelper.updateData(editTextId.getText().toString(), editTextName.getText().toString(), editTextEmail.getText().toString(), editTextCC.getText().toString());
               if (isUpdate == true){
                   Toast.makeText(MainActivity.this, "Updation Succeded", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(MainActivity.this, "Updation failed", Toast.LENGTH_SHORT).show();
               }
           }
       });


    }

    private void showMessage(String title , String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}