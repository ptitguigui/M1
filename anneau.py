import socket
import sys
import functools
from PyQt4 import QtGui

HOST = 'localhost'
PORT = 21567
BUFSIZ = 1024
ADDR = (HOST, PORT)

class ClientWindow(QtGui.QMainWindow):
    def __init__(self):
        QtGui.QMainWindow.__init__(self)
        self.setGeometry(1500, 650, 500, 500)

        self._control = QtGui.QWidget()
        self.setCentralWidget(self._control)

        l = QtGui.QVBoxLayout(self._control)
        for i in range(5):
            name = 'test %d' % i
            b = QtGui.QPushButton(name)
            l.addWidget(b)
            b.pressed.connect(functools.partial(self.onButtonClick, name))

        self.show()

    def onButtonClick(self, buttonName):
        print('connecting to server')
        tcpCliSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        tcpCliSock.connect(ADDR)
        print('Sending name %s' % buttonName)
        tcpCliSock.send(buttonName)
        tcpCliSock.close()


def launch():
    app = QtGui.QApplication(sys.argv)
    ex = ClientWindow()
    sys.exit(app.exec_())

if __name__ == '__main__':
    launch()