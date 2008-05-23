package hudson.remoting;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * {@link InputStream} connected to socket.
 *
 * <p>
 * Unlike plain {@link Socket#getOutputStream()}, closing the stream
 * does not close the entire socket, and instead it merely partial-close
 * a socket in the direction.
 *
 * @author Kohsuke Kawaguchi
 */
public class SocketOutputStream extends FilterOutputStream {
    private final Socket socket;

    public SocketOutputStream(Socket socket) throws IOException {
        super(socket.getOutputStream());
        this.socket = socket;
    }

    @Override
    public void close() throws IOException {
        socket.shutdownOutput();
    }
}
