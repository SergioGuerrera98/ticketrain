package com.corso.ticketrain.application;

import com.corso.ticketrain.config.AppConfig;
import com.corso.ticketrain.dao.DaoInterface;
import com.corso.ticketrain.dao.interfacce.TicketDaoInterface;
import com.corso.ticketrain.dao.interfacce.TrenoDaoInterface;
import com.corso.ticketrain.model.*;
import com.corso.ticketrain.service.TicketService;
import com.corso.ticketrain.service.TrenoService;
import com.corso.ticketrain.treno.builder.TrenoBuilder;
import com.corso.ticketrain.treno.exceptions.TrenoException;
import com.corso.ticketrain.treno.factory.VagoneFactory;
import com.corso.ticketrain.treno.model.Passeggeri;
import com.corso.ticketrain.treno.model.Treno;
import com.corso.ticketrain.treno.model.Vagone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class DatabasePopulation {

    private static final String[] names = {"Aaran", "Aaren", "Aarez", "Aarman", "Aaron", "Aaron-James", "Aarron", "Aaryan",
            "Alan", "Alistair", "Alistar", "Alister", "Cormak", "Corran", "Corrie", "Cory", "Cosmo", "Coupar", "Craig", "Craig-James", "Crawford", "Creag",
            "Daanyaal", "Daegan", "Daegyu", "Damian", "Damien", "Damon", "Dan", "Danar", "Dane", "Danial", "Daniel", "Daniele",
            "Elias", "Elijah", "Eliot", "Elisau", "Eljay", "Emil", "Emile", "Emir", "Emlyn", "Emmanuel", "Emmet",
            "Fergus", "Ferre", "Fezaan", "Fiachra", "Fikret", "Filip", "Filippo", "Finan", "Findlay", "Findlay-James",
            "George", "Georgia", "Georgy", "Gerard", "Ghyll", "Giacomo", "Gian", "Giancarlo", "Gianluca",
            "Hamish", "Hamza", "Hamzah", "Han", "Hansen", "Hao", "Hareem", "Hari", "Harikrishna", "Haris",
            "Ilyaas", "Ilyas", "Iman", "Immanuel", "Inan", "Indy", "Ines", "Innes", "Ioannis", "Ireayomide",
            "Jadon", "Jadyn", "Jae", "Jagat", "Jago", "Jaheim", "Jahid", "Jahy", "Jai", "Jaida", "Jaiden", "Jaidyn",
            "Kainin", "Kainui", "Kairn", "Kaison", "Kaiwen", "Kajally", "Kajetan", "Kalani", "Kale", "Kaleb" };
    private static final String[] lastNames = { "Oisin", "Ojima-Ojo", "Okeoghene", "Olaf", "Ola-Oluwa", "Olaoluwapolorimi",
            "Pacey", "Padraig", "Paolo", "Pardeepraj", "Parkash", "Parker", "Pascoe",
            "Rafferty", "Rafi", "Raheem", "Rahil", "Rahim", "Rahman", "Raith", "Raithin", "Raja", "Rajab-Ali", "Rajan",
            "Saad", "Sabeen", "Sachkirat", "Saffi", "Saghun", "Sahaib", "Sahbian", "Sahil", "Saif",
            "Tadhg", "Taegan", "Taegen", "Tai", "Tait", "Taiwo", "Talha", "Taliesin", "Talon", "Talorcan", "Tamar",
            "Tamiem", "Tammam", "Tanay", "Tane", "Tanner", "Tanvir", "Tanzeel", "Taonga", "Tarik", "Tariq-Jay",
            "Umar", "Umer", "Umut", "Urban", "Uri", "Usman", "Uzair", "Uzayr", "Valen", "Valentin", "Victory",
            "Wei", "Wen", "Wesley", "Wesley-Scott", "Wiktor", "Wilkie", "Windsor", "Wojciech", "Woyenbrakemi",
            "Xiao", "Xida", "Xin", "Xue", "Yadgor", "Yago", "Yahya", "Yakup", "Yang", "Yanick", "Yann",
            "Zakaria", "Zakariya", "Zakary", "Zaki", "Zakir", "Zakk", "Zamaar", "Zander", "Zane", "Zarran"};

    private static List<Treno> setTreno = null;
    private static List<Citta> setCitta = null;
    private static List<Ticket> setTicket = null;
    private static List<User> setUsers = null;
    private static List<Paese> paeseList;

    public static void main(String[] args) throws IOException, TrenoException {
        AnnotationConfigApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);

        TrenoDaoInterface trenoDao = (TrenoDaoInterface) factory.getBean("trenoDao");
        Treno t = new TrenoBuilder(new VagoneFactory()).costruisciTreno("HP");
        trenoDao.update(t);
        t.getId();
        t.getVagoni();
        if (false)
            createCities(factory);
        else {
            DaoInterface<Paese> paeseDao = (DaoInterface<Paese>) factory.getBean("paeseDao");
            DaoInterface<Citta> cittaDao = (DaoInterface<Citta>) factory.getBean("cittaDao");

            setCitta = cittaDao.retrieve();
        }
        if (false)
            makeTrains(factory);
        else {
            //TrenoDaoInterface trenoDao = (TrenoDaoInterface) factory.getBean("trenoDao");
            setTreno = trenoDao.retrieve();
        }
        if (false)
            makeTickets(factory);
        else {
            TicketDaoInterface ticketDao = (TicketDaoInterface) factory.getBean("ticketDao");
            setTicket = ticketDao.retrieve();
        }
        if (false)
            makeUsers(factory);
        else {
            DaoInterface<User> userDao = (DaoInterface<User>) factory.getBean("userDao");
            setUsers = userDao.retrieve();
        }
        if (false)
            makeUserTicket(factory);
    }

    public static void createCities(AnnotationConfigApplicationContext factory) throws IOException {
        DaoInterface<Paese> paeseDao = (DaoInterface<Paese>) factory.getBean("paeseDao");
        DaoInterface<Citta> cittaDao = (DaoInterface<Citta>) factory.getBean("cittaDao");

        Paese italia = new Paese("Italia");
        Paese germania = new Paese("Germania");
        Paese francia = new Paese("Francia");

        paeseDao.create(italia);
        paeseDao.create(germania);
        paeseDao.create(francia);

        //ita
        List<Citta> citta = getCittaJson("./ic.json", italia);
        //fran
        citta.addAll(getCittaJson("./fc.json", francia));
        //ger
        citta.addAll(getCittaJson("./gc.json", germania));

        for (Citta c : citta) {
            cittaDao.create(c);
            System.out.println("Added city: " + c.getNomeCitta() + " (" + c.getPaese_Id().getNomePaese() + ")");
        }

        Collections.shuffle(citta);
        setCitta = citta.subList(0, 200);
    }
    private static List<Citta> getCittaCsv(String path, Paese paese, String separator, int index) {
        List<Citta> citta = new ArrayList<>();

        //ita
        File f;
        Scanner scanner = null;
        try {
            f = new File(path);
            scanner = new Scanner(new FileReader(f));
            scanner.nextLine();
            while (scanner.hasNext()) {
                String[] s = scanner.nextLine().split(separator);
                citta.add(new Citta( s[index].toLowerCase().replaceAll("\"", ""), paese));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (scanner != null) scanner.close();
        }

        return citta;
    }
    private static List<Citta> getCittaJson(String path, Paese paese) throws IOException {
        List<Citta> citta = new ArrayList<>();
        Reader reader = null;
        try {
            // create Gson instance
            Gson gson = new Gson();

            // create a reader
            reader = Files.newBufferedReader(Paths.get(path));
            Set<Citta> set = gson.fromJson(reader, new TypeToken<Set<Citta>>() {}.getType());

            for (Citta cj : set) {
                cj.setPaese_Id(paese);
                //System.out.println(cfj);
                //Citta c = new Citta(cj.getName(), paese);
                citta.add(cj);
            }

            // close reader
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }

        return citta;
    }

    private static void makeTrains(AnnotationConfigApplicationContext factory) throws TrenoException {
        TrenoDaoInterface trenoDao = (TrenoDaoInterface) factory.getBean("trenoDao");
        TrenoService trenoService = new TrenoService(trenoDao);

        String[] treni = {"HPPP", "HPPH", "HPPRPP", "HPRP", "HPPPP", "HPPP", "HPPPH", "HP"};
        List<Treno> set = new ArrayList<>();
        VagoneFactory vf = new VagoneFactory();

        for (String sigla : treni) {
            set.add(trenoService.addTrain2(sigla, vf));
            System.out.println("Added treno: " + sigla);
        }

        setTreno = set;
    }

    private static void makeTickets(AnnotationConfigApplicationContext factory) {
        LocalDateTime now = LocalDateTime.now();
        setTicket = new ArrayList<>();
        Random friend = new Random();
        TicketDaoInterface ticketDao = (TicketDaoInterface) factory.getBean("ticketDao");

        for (Treno treno : setTreno) {
            for (Vagone vagone : treno.getVagoni()) {
                if (vagone instanceof Passeggeri p) {
                    LocalDateTime partenza = LocalDateTime.of(now.getYear(), now.getMonth().getValue(), now.getDayOfMonth(), friend.nextInt(18) + 6, friend.nextInt(12) * 5, 0).plusDays(friend.nextInt(15));
                    Ticket ticket = new Ticket(createRandomString(6),
                            partenza, partenza.plusHours(friend.nextInt(1, 5)).plusMinutes(friend.nextInt(60)),
                            setCitta.get(friend.nextInt(setCitta.size())).getNomeCitta(), setCitta.get(friend.nextInt(setCitta.size())).getNomeCitta(),
                            (2 - p.getClasse()) * 11f, treno, p);
                    setTicket.add(ticket);
                    System.out.println("Added ticket: " + ticket);
                    ticketDao.create(ticket);
                }
            }
        }
    }
    private static String createRandomString(int len) {
        Random friend = new Random();
        StringBuilder s = new StringBuilder();
        while (s.length() < len) {
            char c = (char) friend.nextInt('A', 'z'+1);
            if (c > 'Z' && c < 'a') continue;
            s.append(c);
        }

        return s.toString();
    }

    private static void makeUsers(AnnotationConfigApplicationContext factory) {
        DaoInterface<User> userDao = (DaoInterface<User>) factory.getBean("userDao");
        DaoInterface<Paese> paeseDao = (DaoInterface<Paese>) factory.getBean("paeseDao");
        paeseList = paeseDao.retrieve();

        int howManyUsers = 15;
        Random friend = new Random();
        Set<User> users = new HashSet<>();

        User admin = new User("admin", "admin", true, new Paese("Italia"));
        while (users.size() < 15) {
            User u = new User(names[friend.nextInt(names.length)] + lastNames[friend.nextInt(lastNames.length)],
                    createRandomString(8), false, paeseList.get(friend.nextInt(paeseList.size())));
            users.add(u);
        }

        for (User u : users) {
            userDao.create(u);

            System.out.println("Added user: " + u.getUsername());
        }

        setUsers = new ArrayList<>(users);
    }

    private static void makeUserTicket(AnnotationConfigApplicationContext factory) {
        DaoInterface<TicketUser> ticketUserDao = (DaoInterface<TicketUser>) factory.getBean("ticketUserDao");
        Random friend = new Random();
        int howManyTicket = 50;
        int i = 0;
        while (i < howManyTicket) {
            Ticket t = setTicket.get(friend.nextInt(setTicket.size()));
            User u = setUsers.get(friend.nextInt(setUsers.size()));
            System.out.println("User: " + u.getUsername() + ", Ticket : " + t.getCodice() + ":");
            for (int j = 0; j < friend.nextInt(5); ++j) {//TODO CHECK < TOTALE POSTI
                TicketUser tu = new TicketUser(u, t, names[friend.nextInt(names.length)],
                        lastNames[friend.nextInt(lastNames.length)], ""+((Passeggeri) t.getVagone_id()).getClasse());
                ticketUserDao.create(tu);

                System.out.println("\tBought ticket: " + tu + ", nome: " + tu.getNome() + " " + tu.getCognome());
                ++i;
            }
        }
    }
}
