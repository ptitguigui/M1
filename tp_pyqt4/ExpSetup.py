import sys

from PyQt5.QtCore import *
from PyQt5.QtGui import *
from PyQt5.QtWidgets import *


class ExpSetup(QDialog):

    def __init__(self):
        super().__init__()
        self.setWindowTitle('Display attachment')

        textField_1 = QTextEdit()
        textField_1.setPlainText("Numero Utilisateur")
        textField_1.setReadOnly(True)

        okButton = QPushButton('&Ok')
        okButton.clicked.connect(self.accept)

        layout = QVBoxLayout()
        layout.addWidget(textField_1)
        layout.addWidget(okButton)
        self.setLayout(layout)

def main(args):
    app = QApplication(args)
    window = QMainWindow()
    setup = ExpSetup()
    window.setCentralWidget(setup)
    window.resize(1024, 800)
    window.show()
    app.exec_()


if __name__ == "__main__":
    print("execution du programme")
    main(sys.argv)
