import SocketServer
import concurrent.futures
import sys
from PyQt4 import QtGui
from PyQt4 import QtCore
from Queue import Queue
import cv2

HOST = 'localhost'
PORT = 21567
BUFSIZ = 1024
ADDR = (HOST, PORT)

# create a global queue object that both the handle() method and the QThread (see later in the code) can access
queue = Queue()

class MyRequestHandler(SocketServer.StreamRequestHandler):
    def handle(self):
        print('...connected from:', self.client_address)        
        data = self.rfile.readline().strip()
        print('Data from client %s' % data)
        queue.put(data)

class ServerWindow(QtGui.QMainWindow):
    def __init__(self):
        QtGui.QMainWindow.__init__(self)
        self._control = QtGui.QWidget()
        self.setCentralWidget(self._control)

        self.l = QtGui.QVBoxLayout(self._control)
        self.cap = cv2.VideoCapture("velo.mp4")
        self.video_frame = QtGui.QLabel()
        self.l.addWidget(self.video_frame)
        self.timer = QtCore.QTimer()
        self.timer.timeout.connect(self.nextFrameSlot)
        self.timer.start(1000.0/30)            
        self.executor = concurrent.futures.ThreadPoolExecutor(max_workers=1)
        self.startServerThread()


        self.show()

    @QtCore.pyqtSlot(str)
    def receive_data(self, data):
        QtGui.QToolTip.showText(QtCore.QPoint(1300, 200), data, self.video_frame)

    def startServerThread(self):
        self.executor.submit(self.startServer)

        # How to get information from the thread while it is still running?
    def nextFrameSlot(self):
        ret, frame = self.cap.read()
        if ret:
            frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
            img = QtGui.QImage(frame, frame.shape[1], frame.shape[0], QtGui.QImage.Format_RGB888)
            pix = QtGui.QPixmap.fromImage(img)
            self.video_frame.setPixmap(pix)
        if not ret:
            self.cap.set(cv2.CAP_PROP_POS_FRAMES, 0)

    def startServer(self):
        print('starting server')
        self.tcpServ = SocketServer.TCPServer(ADDR, MyRequestHandler)
        print('waiting for connection...')
        self.tcpServ.serve_forever()

        # How to get information from the client (custom request handler)
        # back to the GUI in a thread safe manner?

# This class runs in a QThread and listens on the end of a queue and emits a signal to the GUI
class MyReceiver(QtCore.QObject):
    mysignal = QtCore.pyqtSignal(str)

    def __init__(self,queue,*args,**kwargs):
        QtCore.QObject.__init__(self,*args,**kwargs)
        self.queue = queue

    @QtCore.pyqtSlot()
    def run(self):
        while True:
            text = self.queue.get()
            self.mysignal.emit(text)


def launch():
    app = QtGui.QApplication(sys.argv)

    ex = ServerWindow()

    # Create thread that will listen on the other end of the queue, and send the text to the textedit in our application
    thread = QtCore.QThread()
    my_receiver = MyReceiver(queue)
    my_receiver.mysignal.connect(ex.receive_data)
    my_receiver.moveToThread(thread)
    thread.started.connect(my_receiver.run)
    thread.start()

    ret_code = app.exec_()
    ex.tcpServ.shutdown()
    sys.exit(ret_code)

if __name__ == '__main__':
    launch()