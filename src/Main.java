import crud.DataService;

public class Main {
    public static void main(String[] args) {
        DataService dataService = new DataService();

        //dataService.getAllEmployees();
        dataService.getEmployeeById(1L);
    }
}
