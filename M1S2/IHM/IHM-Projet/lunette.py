import SocketServer
import concurrent.futures
import sys
from HTMLParser import HTMLParser
from PyQt4.QtGui import *
from PyQt4.QtCore import *
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

class ServerWindow(QMainWindow):
    def __init__(self):
        QMainWindow.__init__(self)
        self._control = QWidget()
        self.setCentralWidget(self._control)

        self.musicList = list()
        self.index = 0
        self.heart = u'\u2665' 
        self.heartless = u'\u2661'
        self.musicNote = u'\u266B'
        self.likes = [False,False,False,False,False]
        #h = HTMLParser()
        self.pause = ''#h.unescape('&#9208;')
        self.play = u'\u25B6'

        self.initMusics()
        self.music = self.musicList[self.index]

        self.l = QVBoxLayout(self._control)
        self.cap = cv2.VideoCapture("velo.mp4")
        self.video_frame = QLabel()
        self.l.addWidget(self.video_frame)
        self.timer = QTimer()
        self.timer.timeout.connect(self.nextFrameSlot)
        self.timer.start(1000.0/30)            
        self.executor = concurrent.futures.ThreadPoolExecutor(max_workers=1)
        self.startServerThread()

        self.show()

    def initMusics(self):
        self.musicList.append(self.pause + " Aya Nakamura - Djadja \t" + self.heartless)
        self.musicList.append(self.pause + " Patrick Sebastien - les sardines \t" + self.heartless)
        self.musicList.append(self.pause + " Jean-Philippe Rameau - Frere Jacques \t" + self.heartless)
        self.musicList.append(self.pause + " Fatal Bazooka - Fou ta cagoule \t" + self.heartless)
        self.musicList.append(self.pause + " Star Academy - La musique \t" + self.heartless)

    def doNext(self):
        self.index += 1
        self.music = self.musicList[self.index%(len(self.musicList))]

    def doPrevious(self):
        self.index -= 1
        self.music = self.musicList[self.index%(len(self.musicList))]

    def doLikeUnlike(self):
        if (self.likes[self.index] == True):
            self.musicList[self.index] =  self.music[:-1] + self.heartless
        else:
            self.musicList[self.index] = self.music[:-1] + self.heart
        
        self.music = self.musicList[self.index]
        self.likes[self.index] = not self.likes[self.index]
    
    def doPlayPause(self):
        print('like')
        
    @pyqtSlot(str)
    def receive_data(self, data):
        #QToolTip.showText(QPoint(1300, 200), data, self.video_frame)
        if data == "Play/Pause":
            self.doPlayPause()
        elif data == "Next":
            self.doNext()
        elif data == "Previous":
            self.doPrevious()
        else:
            self.doLikeUnlike()
            

    def startServerThread(self):
        self.executor.submit(self.startServer)

        # How to get information from the thread while it is still running?
    def nextFrameSlot(self):
        ret, frame = self.cap.read()
        if ret:
            frame = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
            img = QImage(frame, frame.shape[1], frame.shape[0], QImage.Format_RGB888)
            pix = QPixmap.fromImage(img)

            self.drawNotification(pix)
            #self.drawGlasses(pix)
            self.video_frame.setPixmap(pix)
        if not ret:
            self.cap.set(cv2.CAP_PROP_POS_FRAMES, 0)

    def drawNotification(self, pix):
        painter = QPainter(pix)

        penRectangle = QPen(Qt.black)
        painter.setBrush(QColor(0, 255, 255, 100))
        penRectangle.setWidth(2)

        painter.setPen(penRectangle)
        rec = QRect(850,100,400,100)
        painter.drawRect(rec)
        painter.drawText(860, 150, self.musicNote + self.music)

        painter.end()

    def drawGlasses(self, pix):
        painterInstance = QPainter(pix)

        penRectangle = QPen(Qt.red)
        penRectangle.setWidth(8)

        painterInstance.setPen(penRectangle)
        painterInstance.drawRect(100,100,500,400)
        painterInstance.setPen(penRectangle)
        painterInstance.drawRect(650,100,500,400)
        #painterInstance.drawLine(600,200,650,200)
        
        path = QPainterPath() 
        path.moveTo(600, 200)
        path.arcTo(QRectF(0,0,3,3), 650, 200 )
        painterInstance.drawPath(path) 

        painterInstance.drawLine(100,100,0,500)
        painterInstance.drawLine(1150,100,1250,500)

        painterInstance.end()

    def startServer(self):
        print('starting server')
        self.tcpServ = SocketServer.TCPServer(ADDR, MyRequestHandler)
        print('waiting for connection...')
        self.tcpServ.serve_forever()

        # How to get information from the client (custom request handler)
        # back to the GUI in a thread safe manner?

# This class runs in a QThread and listens on the end of a queue and emits a signal to the GUI
class MyReceiver(QObject):
    mysignal = pyqtSignal(str)

    def __init__(self,queue,*args,**kwargs):
        QObject.__init__(self,*args,**kwargs)
        self.queue = queue

    @pyqtSlot()
    def run(self):
        while True:
            text = self.queue.get()
            self.mysignal.emit(text)


def launch():
    app = QApplication(sys.argv)

    ex = ServerWindow()

    # Create thread that will listen on the other end of the queue, and send the text to the textedit in our application
    thread = QThread()
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