import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class DataBase 
{
    /* Atributos */
    private String file_path;

    private File file;
    private FileReader reader;
    private BufferedReader buffer;

    /* Metodos */
    public DataBase(String file_path_arg) {
        file_path = file_path_arg;

        try {
            file = new File(file_path);
            reader = new FileReader(file);  
            buffer = new BufferedReader(reader);  
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        
    }

    public String get_all_data() {
        String line = "", full_data = "";
        try {
            while((line = buffer.readLine()) != null) {
                full_data += line + "\n";
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        return full_data;
    }

    public String filter(String query) {
        String line = "", section = "";

        try {
            while((line = buffer.readLine()) != null) {
                section = "";
                for(char c : line.toCharArray()) {
                    if(c != ';') {
                        section += c;
                    }
                    else {
                        if(section.equals(query)) {
                            return line;
                        }
                        section = "";
                    }
                }
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public String filter_the_line(String line, int query_frag_id) {
        String local_frag = "";
        int pointer = 0;

        for(char c : line.toCharArray()) {
            if(c != ';') {
                local_frag += c;
            }
            else {
                if(query_frag_id == pointer) {
                    return local_frag;
                }

                local_frag = "";
                pointer++;
            }
        }

        return "";
    }

    public int num_lines() {
        String line;
        int total_line = 0;

        try {
            while((line = buffer.readLine()) != null) {
                total_line++;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return total_line;
    }
}
