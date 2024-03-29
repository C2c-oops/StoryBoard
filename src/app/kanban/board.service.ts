import { Injectable } from '@angular/core';
import { AngularFireAuth } from '@angular/fire/auth';
import { AngularFirestore } from '@angular/fire/firestore';
import firebase from 'firebase/app';
import { switchMap, map } from 'rxjs/operators';
import { Board, Task } from './board.model';


@Injectable({
  providedIn: 'root'
})
export class BoardService {

  constructor(private afAuth: AngularFireAuth, private db: AngularFirestore) { }

  //for creating new board to current user
  async createBoard(data: Board) {
    const user = await this.afAuth.currentUser;
    return this.db.collection('boards').add({
      ...data,
      uid: user?.uid,
      tasks: [{description: 'Hello..!', label: 'yellow'}]
    });
  }

  //for getting boards of current user
  getUserBoards() {
    return this.afAuth.authState.pipe(
      switchMap(user => {
        if (user) {
          return this.db
          .collection<Board>('boards', ref =>
            ref.where('uid', '==', user.uid).orderBy('priority')
          ).valueChanges({idField: 'id'});
        } else {
          return [];
        }
      })
    );
  }

  //sort boards according to priority
  sortBoards(boards: Board[]) {
    const db = firebase.firestore();
    const batch = db.batch();
    const refs = boards.map(b => db.collection('boards').doc(b.id));
    refs.forEach((ref, idx) => batch.update(ref, { priority: idx }));
    batch.commit();
  }

  //for deleting board
  deleteBoard(boardId: string) {
    return this.db
      .collection('boards')
      .doc(boardId)
      .delete();
  }

  // for updating task on board
  updateTasks(boardId: string, tasks: Task[]) {
    return this.db
    .collection('boards')
    .doc(boardId)
    .update({ tasks });
  }

  //for removing specific task from the board
  removeTask(boardId: string, task: Task) {
    return this.db
      .collection('boards')
      .doc(boardId)
      .update({
        tasks: firebase.firestore.FieldValue.arrayRemove(task)
      });
  }

}