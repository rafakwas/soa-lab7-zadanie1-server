import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@ManagedBean
@ApplicationScoped
@Named("listcontroller")
public class ListControllerBean {

    @EJB(lookup = "java:global/repository-test/RepositoryTestBean!RepositoryRemote")
    RepositoryRemote repositoryRemote;

    private List<Integer> topics;

//    final static String JMS_USERNAME="user";
//    final static String JMS_PASSWORD="user";
//    final static String WILDFLY_REMOTING_URL="http-remoting://localhost:8080";
//
//    @Resource(mappedName = "java:/myJmsTest/MyConnectionFactory")
//    private ConnectionFactory cf;
//    @Resource(mappedName = "java:/myJmsTest/MyQueue")
//    private Queue queueExample;
//    private Connection connection;

    public void addRow() {
        repositoryRemote.addTopic();
    }

    public void addMessage(int number) {
//        System.out.println("addMessage to topic " + number);
//        System.out.println("JMSService: sendMessage START");
//        try {
//            connection = cf.createConnection(JMS_USERNAME,JMS_PASSWORD);
//            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            System.out.println("Connection created");
//            MessageProducer publisher = session.createProducer(queueExample);
//            System.out.println("producer created : queueExample");
//            connection.start();
//            System.out.println("connection started");
//            TextMessage message = session.createTextMessage("Wiadomość do subskrybentów topicu nr " + number);
//            System.out.println("message created");
//            publisher.send(message);
//            System.out.println("message send through publisher");
//        }
//        catch (Exception exc) {
//            System.out.println("Błąd w wysyłaniu wiadomosci:" + exc.toString());
//        }
//        finally {if (connection != null) {
//            try {
//                connection.close();
//                connection = null;
//            } catch (JMSException e) {
//                System.out.println("BŁĄD w zamykaniu połączenia: " + e.toString());
//            }
//        }
//        }
//        System.out.println("JMSService: sendMessage END");
    }



    /*-------------------------------------------------------------------------------*/
    public List<Integer> getTopics() {
        return repositoryRemote.getTopics();
    }
}
