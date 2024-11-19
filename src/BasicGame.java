import nl.saxion.app.SaxionApp;

import nl.saxion.app.interaction.GameLoop;
import nl.saxion.app.interaction.KeyboardEvent;
import nl.saxion.app.interaction.MouseEvent;

import java.util.ArrayList;

public class BasicGame implements GameLoop {
    ArrayList<Ball> balls;

    public static void main(String[] args) {
        SaxionApp.startGameLoop(new BasicGame(), 1000, 800, 10);
    }

    @Override
    public void init() {
        balls = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            balls.add(createRandomBall());
        }
    }

    public Ball createRandomBall() {
        Ball ball = new Ball();
        ball.x = SaxionApp.getRandomValueBetween(100,900);
        ball.y = SaxionApp.getRandomValueBetween(100,700);
        ball.speedX = SaxionApp.getRandomValueBetween(-5, 5);
        ball.speedY = SaxionApp.getRandomValueBetween(-5,5);
        ball.radius = SaxionApp.getRandomValueBetween(2,15);
        ball.color = SaxionApp.getRandomColor();
        return ball;
    }

    public void updateBall(Ball ball) {
        ball.x += ball.speedX;
        if (ball.x>SaxionApp.getWidth()) {
            ball.x = SaxionApp.getWidth();
            ball.speedX = -ball.speedX;
            SaxionApp.playSound("BasicGame/resources/impact.ogg");
        }
        if (ball.x<0) {
            ball.x = 0;
            ball.speedX = -ball.speedX;
            SaxionApp.playSound("BasicGame/resources/impact.ogg");
        }
        if (ball.y>SaxionApp.getHeight()) {
            ball.y = 0;
        }
        if (ball.y<0) {
            ball.y = SaxionApp.getHeight();
        }
        ball.y += ball.speedY;
    }

    @Override
    public void loop() {
        SaxionApp.clear();
        for (Ball ball:balls) {
            updateBall(ball);
            drawBall(ball);
        }
    }

    private void drawBall(Ball ball) {
        SaxionApp.setFill(ball.color);
        SaxionApp.setBorderColor(ball.color);
        SaxionApp.drawCircle(ball.x, ball.y, ball.radius);
    }

    @Override
    public void keyboardEvent(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKeyCode() == KeyboardEvent.VK_SPACE) {
            Ball ball = balls.get(SaxionApp.getRandomValueBetween(0, balls.size()));
            ball.color = SaxionApp.getRandomColor();
        }
    }

    @Override
    public void mouseEvent(MouseEvent mouseEvent) {
    }
}






