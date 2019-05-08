import sys
from PyQt5.QtWidgets import *
from PyQt5.QtGui import *
from PyQt5.QtCore import *
from CanvasDessin import *
      
class Dessin(QMainWindow):

        def __init__(self, canvas):
                super().__init__()
                self.canvas = canvas
                self.setCentralWidget(self.canvas)

        def addMenuAction(self,img, nom, shortcut, tip):
                newAct = QAction(QIcon(img), nom, self)
                newAct.setShortcut( shortcut )
                newAct.setToolTip(tip)
                newAct.setStatusTip(tip)
                newAct.setEnabled( True )
                return newAct 

        def open(self):
                color = QColorDialog.getColor()

                if color.isValid():
                        print(color.name())
                        self.canvas.color = color
        def valuechange(self):
                print("width changed to " + str(self.sl.value()))
                self.canvas.width = self.sl.value()
        def reset(self):
                self.canvas = CanvasDessin()
                self.setCentralWidget(self.canvas)
                self.sl.setValue(2)

        def addMenuBar(self):
                bar = self.menuBar()
                fichierMenu = bar.addMenu("Option")
                
                newAct = self.addMenuAction("color.jpg", "Select Color", "Ctrl+l", "Select Color to Use")
                
                fichierMenu.addAction(newAct)
                newAct.triggered.connect(self.open)
                        

                fileToolBar = QToolBar("file")
                fileToolBar.addAction(newAct)
                self.addToolBar(fileToolBar)

                self.sl = QSlider(Qt.Horizontal)
                self.sl.setMinimum(2)
                self.sl.setMaximum(10)
                self.sl.setValue(2)
                self.sl.setTickPosition(QSlider.TicksBelow)
                self.sl.setTickInterval(2)
                self.sl.valueChanged.connect(self.valuechange)
                fileToolBar.addWidget(self.sl)

                self.b1 = QPushButton("Reset")
                self.b1.setCheckable(True)
                self.b1.toggle()
                self.b1.clicked.connect(self.reset)
                fileToolBar.addWidget(self.b1)
                self.setStatusBar(QStatusBar())


def main(args):
        app = QApplication(args)
        canvas = CanvasDessin()
        dessin = Dessin(canvas)
        dessin.addMenuBar()
        dessin.show()
        sys.exit(app.exec_())

        
if __name__ == "__main__":
       
        print("execution du programme")
        main(sys.argv)
       
        
