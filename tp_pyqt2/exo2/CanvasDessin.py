import sys
from PyQt5.QtWidgets import *
from PyQt5.QtGui import *
from PyQt5.QtCore import *
from Trace import *


class CanvasDessin(QWidget):
        trace = Trace(2,QColor(Qt.red))
        
        def __init__(self):
                super().__init__()
                self.setMinimumSize(1000,800)
                self.listTrace = list()
        
        def paintEvent(self, event):
                painter = QPainter(self)
                path = QPainterPath

                
                
                painter.drawPath(path)
                self.update()
                
        def mouseReleaseEvent(self, event):
                print("release")
                self.listTrace.append(self.trace)
                self.trace.list = list()
                self.update()
                      
        def mousePressEvent(self, event):
                print("press")
                self.trace.list.append(event.pos())
                self.update()
        
        def mouseMoveEvent(self, event):
                print("drag")
                self.trace.list.append(event.pos())
                self.update()
        

