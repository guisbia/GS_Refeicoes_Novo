package tools;

/**
 *
 * @author bianc
 */
import tools.DateChooser;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class JDateTextField extends JFormattedTextField {

    public static MaskFormatter formatador;
    private final String mesAtual = new SimpleDateFormat("MM").format(new Date());
    private final String anoAtual = new SimpleDateFormat("yyyy").format(new Date());

    static {
        try {
            formatador = new MaskFormatter("##/##/####");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

public   JDateTextField() {
        super(formatador);
        setFont(new Font("Courier", Font.PLAIN, 12));
        setColumns(13);
        addFocusListener(new DateFieldFocusListener());
        addKeyListener(new DateFieldKeyListener());
    }

    public Date getDate() {
        String dataStr = getText().substring(0, 10);
        if (dataStr.equals("  /  /    ")) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        try {
            return df.parse(dataStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDate(Date data) {
        setText(new SimpleDateFormat("dd/MM/yyyy").format(data));
    }

    private void completaData() {

        String[] partes = getText().split("/");
        String dataStr = getText().substring(0, 10);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        if (partes[0].equals("  ")) {
            dataStr = df.format(new Date());
        } else // mes
        if (partes[1].equals("  ")) {
            dataStr = partes[0] + "/" + mesAtual + "/" + anoAtual;
        } else // ano
        if (partes[2].substring(0, 2).equals("  ")) // note o substring por causa do dia da semana
        {
            dataStr = partes[0] + "/" + partes[1] + "/" + anoAtual;
        }

        df.setLenient(false);
        try {
            Date d = df.parse(dataStr);
            setText(new SimpleDateFormat("dd/MM/yyyy").format(d));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "O valor de data digitado não é válido", "Erro de conversão", JOptionPane.ERROR_MESSAGE);
            setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            requestFocus();
        }
    }

    public class DateFieldFocusListener implements FocusListener {

        public void focusGained(FocusEvent e) {
        }

        public void focusLost(FocusEvent e) {
            completaData();
        }
    }

    private class DateFieldKeyListener implements KeyListener {

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                Point p = getLocationOnScreen();
                DateChooser dc = new DateChooser((JFrame) null, "Escolha uma data", p.x, p.y + getHeight());
                Date newDate = dc.select(getDate());
                if (newDate != null) {
                    setText(new SimpleDateFormat("dd/MM/yyyy").format(newDate));
                }
                setSelectionStart(0);
                setSelectionEnd(0);
            }
        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }

    }
}
