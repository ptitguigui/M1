import sys

from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
from BubbleWidget import *


class MainBubble(QMainWindow):

    def __init__(self):
        super().__init__()


class Target():

    defaultCol = QColor(Qt.cyan)
    toSelectCol = QColor(Qt.red)
    highlightCol = QColor(Qt.darkBlue)

    def __init__(self, x, y, size):
        self.x = x
        self.y = y
        self.size = size
        self.toSelect = False
        self.highlighted = False

    def paint(self, painter):
        painter.setRenderHints(QPainter.Antialiasing)
        pen = QPen(QColor(Qt.black))
        pen.setWidth(2)

        ellipse = QRect(QPoint(self.x, self.y), QSize(self.size, self.size))

        if self.toSelect:
            painter.setBrush(self.toSelectCol)
        elif self.highlighted:
            painter.setBrush(self.highlightCol)
        else:
            painter.setBrush(self.defaultCol)

        painter.setPen(pen)
        painter.drawEllipse(ellipse)


def main(args):
    app = QApplication(args)
    window = MainBubble()
    bubbleWidget = BubbleWidget()
    window.setCentralWidget(bubbleWidget)
    window.resize(1024, 800)
    window.show()
    app.exec_()


if __name__ == "__main__":
    print("execution du programme")
    main(sys.argv)
