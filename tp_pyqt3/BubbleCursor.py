import sys
import time

from random import randint
from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *
from scipy.spatial import distance

from BubbleWidget import *


class BubbleCursor():

    defaultCol = QColor(Qt.green)

    def __init__(self, targets):
        self.x = 0
        self.y = 0
        self.size = 40
        self.targets = targets
        self.closest = None
        self.currentSelect = None

    def paint(self, painter):
        painter.setRenderHints(QPainter.Antialiasing)
        pen = QPen(QColor(Qt.black))
        pen.setWidth(2)

        ellipse = QRect(QPoint(self.x-(self.size/2), self.y-(self.size/2)),
                        QSize(self.size, self.size))

        painter.setBrush(self.defaultCol)
        painter.setPen(pen)
        painter.drawEllipse(ellipse)

    def move(self, x, y):
        self.x = x
        self.y = y
        dstMin = 10000

        for target in self.targets:
            a = (target.x, target.y)
            b = (self.x, self.y)
            dst = distance.euclidean(a, b)
            if(dstMin > dst):
                if (self.closest is not None):
                    self.closest.highlighted = False
                self.closest = target
                dstMin = dst

        self.closest.highlighted = True
        self.size = (dstMin * 2) - self.closest.size

    def selectTarget(self):
        random = randint(0, 99)
        target = self.targets[random]
        target.toSelect = True
        self.currentSelect = target
        timer = time.time()
        print(timer)
