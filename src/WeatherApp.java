import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherApp extends JFrame {
    private JTextField locationInput;
    private JButton update;
    private JLabel temp1,prec1, wind1, humid1;
    private JTextArea forecastArea;
    private String currentLocation;
    public WeatherApp(){
        setTitle("WeatherStation");
        getContentPane().setPreferredSize(new Dimension (800, 600));

        JPanel northPanel = new JPanel();
        JPanel inputPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        northPanel.setBackground(Color.lightGray);
        JLabel label = new JLabel("Location: ");
        locationInput = new JTextField(20);
        update = new JButton("Check Weather");
        inputPanel.add(label);
        inputPanel.add(locationInput);
        inputPanel.add(update);
        JLabel statusLabel = new JLabel("No city selected", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        northPanel.add(inputPanel, BorderLayout.CENTER);
        northPanel.add(statusLabel, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        JPanel gridPanel = new JPanel();
        JLabel heading1 = new JLabel("Current Weather Information", JLabel.CENTER);
        centerPanel.setLayout(new BorderLayout());
        gridPanel.setLayout(new GridLayout(2, 2, 10, 10));
        heading1.setFont(new Font("Arial", Font.BOLD, 18));
        centerPanel.add(heading1, BorderLayout.NORTH);
        temp1 = new JLabel("Temperature: ____", JLabel.CENTER);
        prec1 = new JLabel("Precipitation: ____", JLabel.CENTER);
        wind1 = new JLabel("Wind Speed: ____", JLabel.CENTER);
        humid1 = new JLabel("Humidity: ____", JLabel.CENTER);
        gridPanel.add(temp1);
        gridPanel.add(prec1);
        gridPanel.add(wind1);
        gridPanel.add(humid1);
        centerPanel.add(gridPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        forecastArea = new JTextArea(15, 50);
        forecastArea.setEditable(false);
        forecastArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(forecastArea);
        JLabel heading2 = new JLabel("Forecast Data", JLabel.CENTER);
        heading2.setFont(new Font("Arial", Font.BOLD, 18));
        southPanel.add(heading2, BorderLayout.NORTH);
        southPanel.add(scrollPane, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentLocation = locationInput.getText().trim();
                if (currentLocation.isEmpty()) {
                    JOptionPane.showMessageDialog(WeatherApp.this, "Please enter the name of a city.");
                    return;
                }
                try {
                    OpenMeteoClient.updateCurrentWeather(currentLocation);
                    temp1.setText("Temperature: " + OpenMeteoClient.getCurrentTemperature() + "°C");
                    prec1.setText("Precipitation: " + OpenMeteoClient.getCurrentPrecipitation() + "mm");
                    wind1.setText("Wind Speed: " + OpenMeteoClient.getCurrentWind() + "km/h");
                    humid1.setText("Humidity: " + OpenMeteoClient.getCurrentHumidity() + "%");

                    OpenMeteoClient.updateForecast(currentLocation);
                    String[] time = OpenMeteoClient.getForecastTime();
                    String[] temp = OpenMeteoClient.getForecastTemperature();
                    String[] precip = OpenMeteoClient.getForecastPrecipitation();
                    String[] wind = OpenMeteoClient.getForecastWind();
                    String[] humid = OpenMeteoClient.getForecastHumidity();
                    forecastArea.setText("");
                    forecastArea.append(String.format("%-20s %-20s %-20s %-20s %s\n", "Time", "Temp", "Precip", "Wind", "Humidity"));
                    for (int i = 0; i < time.length; i++) {
                        String forecast = String.format("%-20s %-20s %-20s %-20s %s%%\n", time[i], temp[i] + "°C", precip[i] + "mm", wind[i] + "km/h", humid[i]);
                        forecastArea.append(forecast);
                    }
                    forecastArea.setCaretPosition(0);
                    statusLabel.setText("Currently showing information for: " + currentLocation);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(WeatherApp.this, "Error retrieving data for" + currentLocation + ": " + err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new WeatherApp();
    }
}