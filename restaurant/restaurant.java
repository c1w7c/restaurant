package restaurant;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class restaurant {
	static List<Table> table = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		showOption();
		int inputOption;
		while((inputOption = input()) != 7) {
			switch(inputOption) {
				case Action.Import : 
					inputToList();
					showOption();
					break;
				case Action.Add : 
					showAdd();
					showOption();
					break;
				case Action.PrintMenu :
					showImportMenu();
					showOption();
					break;
				case Action.Order :
					showOrder();
					showOption();
					break;
				case Action.Account :
					showAccount();
					showOption();
					break;
				case Action.PrintOrder : 
					showPrintOrder();
					showOption();
					break;
			}
		}
		
	}
	
	public static List<Carte> inputToList() throws Exception{
		List<Carte> carteList = new ArrayList<>();
		FileReader file = new FileReader("menu.txt");
		BufferedReader br = new BufferedReader(file);
		String[] item;
		while(br.ready()) {
			//System.out.println(br.readLine());
			item = br.readLine().split(" ");
			carteList.add(new Carte(item[0],Integer.parseInt(item[1]),
						 Integer.parseInt(item[2])));
		}
		return carteList;
	}
	
	public static void showImportMenu() throws Exception{
		List<Carte> carteList = inputToList();
		for(Carte c : carteList) {
			System.out.printf("%s       \t%d\t%d\n",c.getName(),c.getPrice(),c.getCalorie());
		}
	}
	
	public static void showAdd() throws Exception{
		List<Carte> carteList = inputToList();
		Carte item = new Carte();
		carteList.add(item);
		Scanner input = new Scanner(System.in);
		System.out.printf("%s","請輸入菜單名");
		item.setName(input.nextLine());
		System.out.printf("%s","請輸入單價");
		item.setPrice(input.nextInt());
		System.out.printf("%s","請輸入卡路里");
		item.setCalorie(input.nextInt());
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("menu.txt"))){
		for(Carte c : carteList) {
			bw.write(c.getName()+ " "+ c.getPrice()+ " "+c.getCalorie());
			bw.newLine();
		}
		}
	}
	
	public static void showOrder() throws Exception{
		List<Carte> carteList = inputToList();
		int countLine=0;
		
		for(int i=1;i<=carteList.size();i++) {
			if( countLine % 5 != 0 || countLine == 0 ) {
				System.out.printf("(%d)%s\t   ",i,carteList.get(i-1).getName());
			}
			else {
				System.out.printf("\n(%d)%s\t   ",i,carteList.get(i-1).getName());
			}
			countLine++;
		}
		System.out.printf("\n%s\n","請輸入桌號");
		int tableInput = input();
		int flag = getTableNum(tableInput); //取得list中桌子號碼的index
		int num=0;
		Table tableNum = table.get(flag);
		System.out.printf("%s\n","輸入菜名編號");
		while(( num = input()) != 0) {
			tableNum.getOrderList().add(carteList.get(num-1));
			tableNum.setTotalPrice(tableNum.getTotalPrice() + carteList.get(num-1).getPrice());
			tableNum.setTotalCal(tableNum.getTotalCal() + carteList.get(num-1).getCalorie());
			System.out.printf("累計%d元、%d卡路里 請輸入菜單編號",tableNum.getTotalPrice(),tableNum.getTotalCal());
		}
	}

	public static void showAccount(){
		System.out.printf("%s\n", "請輸入買單桌號");
		int inputNum = input();
		try {
			for(Carte c : table.get(getTableNum(inputNum)).getOrderList()) {
				System.out.printf("菜名 : %s\t%d\n",c.getName(),c.getPrice());
			}
			System.out.printf("合計 = %d\n",table.get(getTableNum(inputNum)).getTotalPrice());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void showPrintOrder() {
		int sum=0;
		System.out.printf("桌次\t菜單項目\t\n");
		for(Table t : table) {
			for(Carte c : t.getOrderList()) {
				System.out.printf("%d\t%s\n",t.getTableNum(),c.getName());
			}
			sum += t.getTotalPrice();
		}
		System.out.println("總共收入 : " + sum);
		
	}

	public static void showOption() {
		System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n","*****吳家小廚神******",
						  "(1)匯入菜單項目","(2)新增菜單項目","(3)列印所有菜單",
						  "(4)顧客點菜作業","(5)會計收款作業","(6)列印所有點菜",
						  "(7)結 束 作 業","請輸入編號以便作業");
	}

	public static int input(){
		Scanner input = new Scanner(System.in);
		return input.nextInt();
	}

	public static int getTableNum(int tableInput) {
		int flag=0;
		for(int i=0;i<table.size();i++) { //找有沒有桌號
			if(table.get(i).getTableNum() == tableInput) {
				flag = i;
			}
		}
		if(flag == 0) { //沒有桌號則新增桌子號碼
			table.add(new Table(tableInput));
			flag = table.size()-1;
		}
		return flag;
	}
}

	
