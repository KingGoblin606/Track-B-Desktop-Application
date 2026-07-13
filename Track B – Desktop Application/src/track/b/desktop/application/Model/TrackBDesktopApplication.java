package track.b.desktop.application.Model;

public class TrackBDesktopApplication 
{

    public static void main(String[] args) 
    {
        DBInitializer.initialize();
        java.awt.EventQueue.invokeLater(() -> new track.b.desktop.application.View.LoginForm().setVisible(true));
    }
}
