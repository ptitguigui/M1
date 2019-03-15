import sys
from PyQt5.QtWidgets import *
from PyQt5.QtGui import *
from PyQt5.QtCore import *
from CanvasDessin import *


        
class Dessin(QMainWindow):

        def __init__(self):
                super().__init__()
                self.canvas = CanvasDessin()
                self.setCentralWidget(self.canvas)

def main(args):
        app = QApplication(args)
        dessin = Dessin()
        dessin.show()
        app.exec_()

        
if __name__ == "__main__":
	print("execution du programme")
	main(sys.argv)
        
